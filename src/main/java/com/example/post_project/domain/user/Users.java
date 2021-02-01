package com.example.post_project.domain.user;

import com.example.post_project.domain.BaseEntity;
import com.example.post_project.domain.post.Post;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Entity
public class Users extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<Post> post;


    @Builder
    public Users(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }


    public Users update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }


}
