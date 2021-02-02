package com.example.post_project.domain.post;

import com.example.post_project.domain.BaseEntity;
import com.example.post_project.domain.user.Users;
import com.example.post_project.web.dto.PostUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Post extends BaseEntity {

    @Column(length = 500,nullable = false)
    private String title;

    @Column(nullable = false)
    private String writer;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn()
    private Users user;


    @Builder
    public Post(String title, String writer, String content,Users user) {
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.user=user;
    }


    public void update(PostUpdateRequestDto requestDto) {
        this.title=requestDto.getTitle();
        this.content=requestDto.getContent();
    }
}
