package com.eliasdolinsek.todolist2024backend.services.auth;

import com.eliasdolinsek.todolist2024backend.dto.auth.CredentialsDto;
import com.eliasdolinsek.todolist2024backend.dto.auth.SignUpDto;
import com.eliasdolinsek.todolist2024backend.dto.auth.UserDto;
import com.eliasdolinsek.todolist2024backend.entities.auth.User;
import com.eliasdolinsek.todolist2024backend.exceptions.AppException;
import com.eliasdolinsek.todolist2024backend.mappers.auth.UserMapper;
import com.eliasdolinsek.todolist2024backend.repositories.auth.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserDto login(CredentialsDto credentialsDto){
        User user = userRepository.findByLogin(credentialsDto.login()).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        if(passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword())){
            return userMapper.toUserDto(user);
        } else {
            throw new AppException("Wrong password", HttpStatus.BAD_REQUEST);
        }
    }

    public UserDto register(SignUpDto signUpDto){
        userRepository.findByLogin(signUpDto.login()).ifPresent(user -> {
            throw new AppException("User already exists", HttpStatus.BAD_REQUEST);
        });

        User user = userMapper.signUpDtoToUser(signUpDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.password())));

        User savedUser = userRepository.save(user);
        return userMapper.toUserDto(savedUser);
    }
}
