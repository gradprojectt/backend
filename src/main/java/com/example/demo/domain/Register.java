package com.example.demo.domain;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;


@RestController
public class Register {

    @GetMapping("/register")
    public String register(){
        User newUser = new User();
        //TODO : add db save function
        return newUser.getId();
    }

}

