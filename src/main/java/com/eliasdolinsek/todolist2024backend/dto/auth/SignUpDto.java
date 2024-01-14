package com.eliasdolinsek.todolist2024backend.dto.auth;

public record SignUpDto (String firstName, String lastName, String login, char[] password) {
    
}
