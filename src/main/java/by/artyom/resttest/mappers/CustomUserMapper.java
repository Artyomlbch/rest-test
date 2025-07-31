package by.artyom.resttest.mappers;

import by.artyom.resttest.dto.UpdateUserDto;
import by.artyom.resttest.entities.User;
import org.springframework.stereotype.Component;

@Component
public class CustomUserMapper {

    public void update(UpdateUserDto updateUserDto, User user) {
        user.setName(updateUserDto.getName());
        user.setEmail(updateUserDto.getEmail());
    }

}
