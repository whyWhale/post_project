package com.example.post_project.web.dto.post;

import com.example.post_project.domain.post.Post;
import com.example.post_project.domain.user.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class PostResponseDto {
    private Long post_id;
    private String title;
    private String writer;
    private String content;
    @ToString.Exclude
    private Users users;


    public PostResponseDto(Post post) {
        this.post_id = post.getId();
        this.title = post.getTitle();
        this.writer =post.getWriter();
        this.content = post.getContent();
        this.users=post.getUser();
    }

}
