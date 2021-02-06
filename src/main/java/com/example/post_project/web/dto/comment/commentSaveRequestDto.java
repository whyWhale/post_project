package com.example.post_project.web.dto.comment;

import com.example.post_project.domain.comment.Comment;
import com.example.post_project.domain.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
@Getter
@NoArgsConstructor
public class commentSaveRequestDto {
    private String writer;
    private String content;
    private Post post;

    public commentSaveRequestDto(String writer, String content) {
        this.writer = writer;
        this.content = content;
    }

    public void setPost(Post post)
    {
        this.post=post;
    }
    public Comment entity(){
        return Comment.builder().content(content).post(post).cs_writer(writer).build();
    }
}
