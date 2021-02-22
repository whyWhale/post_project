package com.example.post_project.web.dto.post;

import com.example.post_project.domain.post.Post;
import com.example.post_project.domain.user.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class PostSaveRequestDto {


    @NotEmpty(message = "title is empty....")
    private String title;
    private String writer;
    @NotEmpty(message = "content is empty....")
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
