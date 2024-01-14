package com.eliasdolinsek.todolist2024backend.mappers.auth;

import com.eliasdolinsek.todolist2024backend.dto.auth.SignUpDto;
import com.eliasdolinsek.todolist2024backend.dto.auth.UserDto;
import com.eliasdolinsek.todolist2024backend.entities.auth.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "id", ignore = true)
    User signUpDtoToUser(SignUpDto signUpDto);
}
