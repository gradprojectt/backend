package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter @Setter
public class User {

    @Id @GeneratedValue
    private Long id;

    @Column(name = "user_id")
    @NotEmpty
    private String userId;
    private String nickName;
    private String pccs;
    private String season;
    private String tone;

}