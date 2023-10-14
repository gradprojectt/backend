package com.example.demo.repository;

import com.example.demo.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User findOne(Long id) {
        return em.find(User.class, id);
    }

    public List<User> findAll() {
        return em.createQuery("select m from User m", User.class)
                .getResultList();
    }

    public List<User> findByUserId(String userId) {
        return em.createQuery("select m from User m where m.userId = :userId", User.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public List<User> findById(String id) {
        System.out.println("repo");
//        where m.id = :id
        return em.createQuery("select m from User m ", User.class)
//                .setParameter("id", id)
                .getResultList();
    }

}
