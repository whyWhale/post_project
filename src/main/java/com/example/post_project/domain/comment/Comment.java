package com.example.post_project.domain.comment;

import com.example.post_project.domain.BaseEntity;
import com.example.post_project.domain.post.Post;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class Comment extends BaseEntity {

    @ManyToOne
    @JoinColumn()
    Post post;
    
    @Column(nullable = false,columnDefinition = "text")
    private String content;

    @Column(nullable = false)
    private String cs_writer;

    @Builder
    public Comment(Post post, String content,String cs_writer) {
        this.post = post;
        this.cs_writer=cs_writer;
        this.content = content;
    }
}
