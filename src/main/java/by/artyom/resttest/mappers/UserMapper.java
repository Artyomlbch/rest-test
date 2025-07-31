package by.artyom.resttest.mappers;

import by.artyom.resttest.dto.RegisterUserDto;
import by.artyom.resttest.dto.UserDto;
import by.artyom.resttest.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    UserDto toDto(User user);

    User fromRegisterUserDto(RegisterUserDto request);

}
