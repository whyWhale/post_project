package com.example.post_project.domain.post;

import com.example.post_project.domain.BaseEntity;
import com.example.post_project.domain.comment.Comment;
import com.example.post_project.domain.user.Users;
import com.example.post_project.web.dto.post.PostUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Getter
@ToString
@DynamicUpdate
@NoArgsConstructor
@Entity
public class Post extends BaseEntity {

    @Column(length = 500,nullable = false)
    private String title;

    @Column(nullable = false)
    private String writer;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn()
    private Users user;

    @ToString.Exclude
    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<Comment> comments;


    @Builder
    public Post(String title, String writer, String content,Users user) {
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.user=user;
    }


    public Post update(PostUpdateRequestDto requestDto) {
        this.title=requestDto.getTitle();
        this.content=requestDto.getContent();
        return this;
    }
}
