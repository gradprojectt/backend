package com.example.demo.service;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(User user) {

        validateDuplicateMember(user); //중복 회원 검증
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateMember(User user) {
        List<User> findUsers = userRepository.findByUserId(user.getUserId());
        if(!findUsers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public User findOne(Long userId) {
        return userRepository.findOne(userId);
    }

}


