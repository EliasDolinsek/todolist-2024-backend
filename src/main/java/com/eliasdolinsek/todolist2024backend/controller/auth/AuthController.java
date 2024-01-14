package com.eliasdolinsek.todolist2024backend.controller.auth;

import com.eliasdolinsek.todolist2024backend.dto.auth.CredentialsDto;
import com.eliasdolinsek.todolist2024backend.dto.auth.SignUpDto;
import com.eliasdolinsek.todolist2024backend.dto.auth.UserDto;
import com.eliasdolinsek.todolist2024backend.services.auth.UserService;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto){
        return ResponseEntity.ok(userService.login(credentialsDto));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDto){
        UserDto user = userService.register(signUpDto);
        return ResponseEntity.created(URI.create("/users/" + user.getId())).body(user);
    }
}
