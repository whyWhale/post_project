package com.example.post_project.web.dto;

import com.example.post_project.domain.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
public class PostListResponseDto {
    Long id;
    private String title;
    private String writer;
    private String content;
    private String created;
    private String modified;

    public PostListResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.writer =post.getWriter();
        this.content = post.getContent();
        this.created=post.getFormattiedCreateDate();
        this.modified=post.getFormattiedModifyDate();
    }
}
