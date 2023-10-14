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
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/user_id")
    public String getUserId() {
        User newUser = new User();
        UUID new_uuid = UUID.randomUUID();
        String new_user_id = new_uuid.toString();
        newUser.setUserId(new_user_id);
//        System.out.println(newUser.toString());
//        System.out.println(newUser.getId().toString());
        this.userService.join(newUser);
        return new_user_id;
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
//            System.out.println(request.getId());

            User user = userService.findUserByUserId(request.getUserId());
//            System.out.println(user.toString());

            if (user != null) {
                return ResponseEntity.ok(new UserInfoResponse(user.getNickName(), user.getPccs(), user.getSeason(), user.getTone()));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/nick_name")
    public ResponseEntity<NicknameResponse> modifyNickname(@RequestBody NicknameRequest request) {
        try {
            User updatedUser = userService.modifyNickname(request.getUserId(), request.getNewNickname());

            if (updatedUser != null) {
                return ResponseEntity.ok(new NicknameResponse(updatedUser.getUserId(), updatedUser.getNickName()));
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

    @Data
    static class NicknameRequest{
        private String userId;
        private String newNickname;
    }

    @Data
    static class NicknameResponse{
        private String userId;
        private String nickName;

        public NicknameResponse(String userId, String nickName){
            this.userId = userId;
            this.nickName = nickName;
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
