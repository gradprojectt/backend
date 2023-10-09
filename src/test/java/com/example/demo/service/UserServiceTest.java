package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired UserService userService;
    @Autowired UserRepository userRepository;
    @Autowired EntityManager em;

    @Test
//    @Rollback(false)
    public void 회원가입() throws Exception{
        //given
        User user = new User();
        user.setUserId("kim");

        //when
        Long savedId = userService.join(user);

        //then
        em.flush();
        Assert.assertEquals(user, userRepository.findOne(savedId));

    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //given
        User user1 = new User();
        user1.setUserId("kim");

        User user2 = new User();
        user2.setUserId("kim");

        //when
        userService.join(user1);
        userService.join(user2); //예외가 발생해야 한다 !!!

        //then
        fail("예외가 발생해야 한다.");
    }

}
