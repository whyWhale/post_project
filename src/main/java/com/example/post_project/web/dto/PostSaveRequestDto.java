package com.example.post_project.web.dto;

import com.example.post_project.domain.post.Post;
import com.example.post_project.domain.user.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import javax.persistence.Column;
import javax.persistence.Lob;

@Getter
@NoArgsConstructor
public class PostSaveRequestDto {
    private String title;
    private String writer;
    private String content;
    private Users user;

    @Builder
    public PostSaveRequestDto(String title, String writer, String content) {
        this.title = title;
        this.writer = writer;
        this.content = content;
    }
    public void setUser(Users user)
    {
        this.user=user;
    }


    public Post entity()
    {
        return Post.builder().title(title).content(content).writer(writer).user(user).build();
    }
}
