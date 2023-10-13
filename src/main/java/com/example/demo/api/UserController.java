package com.example.demo.api;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/{user_id}")
    public CreateUserResponse getUser(@PathVariable("user_id") String userId ) {
        return new CreateUserResponse(userId);
    }

    /*@PostMapping("/user_id")
    public CreateUserResponse saveUser(@RequestBody @Valid CreateUserRequest request) {

        User user = new User();
        user.setUserId(request.getUserid());

        userService.join(user);
        return new CreateUserResponse(request.getUserid());
    }*/

    @PostMapping("/user_info")
    public ResponseEntity<UserInfoResponse> getUserInfo(@RequestBody UserInfoRequest request) {
        try {
            User user = userService.findUserByUserId(request.getUserId());

            if (user != null) {
                return ResponseEntity.ok(new UserInfoResponse(user.getNickName(), user.getPccs(), user.getSeason(), user.getTone()));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    /*public ResponseEntity<UserInfoResponse> getUserInfo(@RequestBody UserInfoRequest request) {
        try {
            User user = userService.findUserByUserId(request.getUserid());

            if (user != null) {
                return ResponseEntity.ok(new UserInfoResponse(user.getNickName(), user.getPersonalColor()));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserInfoResponse("userId not found"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UserInfoResponse("Unknown error occurred"));
        }
    }*/

   /* @Data
    static class CreateUserRequest {
        private String userid;
    }*/

    @Data
    static class CreateUserResponse {
        private String userId;

        public CreateUserResponse(String userId) {
            this.userId = userId;
        }
    }

    @Data
    static class UserInfoRequest{
        private String userId;
    }

    @Data
    static class UserInfoResponse{
        private String nickName;
        private String pccs;
        private String season;
        private String tone;

        // Constructor for successful response.
        public UserInfoResponse(String nickName, String pccs, String season, String tone){
            this.nickName = nickName;
            this.pccs = pccs;
            this.season = season;
            this.tone = tone;
        }
    }


   /* @Data
    static class UserInfoRequest{
        private String userid;
    }*/

    /*@Data
    static class UserInfoResponse{
        private String nickname;
        private String personalcolor;
        private String error;

        // Constructor for successful response.
        public UserInfoResponse(String nickname, String personalcolor){
            this.nickname = nickname;
            this.personalcolor = personalcolor;
        }

        // Constructor for error response.
        public UserInfoResponse(String error){
            this.error=error;
        }
    }*/
}
