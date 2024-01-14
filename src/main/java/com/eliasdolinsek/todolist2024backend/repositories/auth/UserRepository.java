package com.eliasdolinsek.todolist2024backend.repositories.auth;

import com.eliasdolinsek.todolist2024backend.entities.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);
}
