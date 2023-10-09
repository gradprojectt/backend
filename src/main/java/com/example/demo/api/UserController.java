package com.example.demo.api;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/user_id")
    public CreateUserResponse saveUser(@RequestBody @Valid CreateUserRequest request) {

        User user = new User();
        user.setUserId(request.getUserid());

        Long id = userService.join(user);
        return new CreateUserResponse(id);
    }

    @Data
    static class CreateUserRequest {
        private String userid;
    }

    @Data
    static class CreateUserResponse {
        private Long id;

        public CreateUserResponse(Long id) {
            this.id = id;
        }
    }
}
