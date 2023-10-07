package com.example.demo.controller;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;

public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public Long register() {
        User newUser = new User();
        userRepository.save(newUser);
        return newUser.getId();
    }
}
