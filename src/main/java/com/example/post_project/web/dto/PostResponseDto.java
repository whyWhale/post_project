package com.example.post_project.web.dto;

import com.example.post_project.domain.post.Post;
import com.example.post_project.domain.user.Users;
import lombok.Getter;

@Getter
public class PostResponseDto {
    Long id;
    private String title;
    private String writer;
    private String content;
    private Users users;


    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.writer =post.getWriter();
        this.content = post.getContent();
        this.users=post.getUser();
    }
}
