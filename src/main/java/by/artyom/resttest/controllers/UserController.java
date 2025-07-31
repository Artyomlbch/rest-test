package by.artyom.resttest.controllers;

import by.artyom.resttest.dto.ChangePasswordRequest;
import by.artyom.resttest.dto.RegisterUserDto;
import by.artyom.resttest.dto.UpdateUserDto;
import by.artyom.resttest.dto.UserDto;
import by.artyom.resttest.entities.ErrorResponse;
import by.artyom.resttest.entities.User;
import by.artyom.resttest.mappers.CustomUserMapper;
import by.artyom.resttest.mappers.UserMapper;
import by.artyom.resttest.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CustomUserMapper customUserMapper;

    @GetMapping
    public List<UserDto> getUsers(
            @RequestParam(required = false, defaultValue = "", name = "sort") String sort) {
//        return userRepository.findAll().stream()
//                .map(user -> new UserDto(user.getId(),
//                        user.getName(), user.getEmail()))
//                .toList();
        if (!Set.of("name", "email").contains(sort)) {
            sort = "name";
        }

        return userRepository.findAll(Sort.by(sort)).stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
//        UserDto userDto = userRepository.findById(id)
//                .map(user -> new UserDto(user.getId(),
//                        user.getName(), user.getEmail()))
//                .orElse(null);

        UserDto userDto = userRepository.findById(id)
                .map(userMapper::toDto)
                .orElse(null);

        if (userDto == null) {
            ErrorResponse error = new ErrorResponse(LocalDateTime.now(),
                    "User with id = " + id + " not found", "User not found");

            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(userDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody RegisterUserDto request) {
        User user = userMapper.fromRegisterUserDto(request);
        User createdUser = userRepository.save(user);

        log.info("User with id: {} created", createdUser.getId());

        return userMapper.toDto(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable(name = "id") Long id,
                                    @RequestBody UpdateUserDto request) {

        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        customUserMapper.update(request, user);
        userRepository.save(user);

        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "id") Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        userRepository.delete(user);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/change-password")
    public ResponseEntity<Void> changePassword(@PathVariable Long id,
                                               @RequestBody ChangePasswordRequest request) {

        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        if (!user.getPassword().equals(request.getOldPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        user.setPassword(request.getNewPassword());
        userRepository.save(user);

        return ResponseEntity.noContent().build();
    }

}
