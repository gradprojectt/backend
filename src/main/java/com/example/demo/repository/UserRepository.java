package com.example.demo.repository;

import com.example.demo.domain.User;
//import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
public interface UserRepository {
    User save(User user);
    //Optional<User> findById(Long id);
    Optional<User> findByName(String id);

    Optional<User> findById(Long id);

    List<User> findAll();
}
