package com.example.post_project.config.auth.dto;

import com.example.post_project.domain.user.Users;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Optional;

@ToString
@Getter
public class SessionUser implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String picture;

    public SessionUser(Users user) {
        this.id=user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }

}