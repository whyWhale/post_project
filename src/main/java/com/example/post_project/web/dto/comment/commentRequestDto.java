package com.example.post_project.web.dto.comment;

import com.example.post_project.domain.comment.Comment;
import com.example.post_project.domain.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
@Getter
@NoArgsConstructor
public class commentRequestDto {
    private String writer;
    private String content;
    private Long postId;
    private Post post;

    public commentRequestDto(String writer, String content,Long postId) {
        this.writer = writer;
        this.content = content;
        this.postId=postId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comment toEntity(){
        return Comment.builder().content(content).post(post).cs_writer(writer).build();
    }
}
