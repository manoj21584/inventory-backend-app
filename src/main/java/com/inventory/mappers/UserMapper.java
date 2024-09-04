package com.inventory.mappers;

import com.inventory.dtos.SignUpDto;
import com.inventory.dtos.UserDto;
import com.inventory.entites.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);

}
