package com.example.post_project.service;

import com.example.post_project.config.auth.dto.SessionUser;
import com.example.post_project.domain.comment.Comment;
import com.example.post_project.domain.comment.CommentRepository;
import com.example.post_project.domain.post.Post;
import com.example.post_project.domain.post.PostRepository;
import com.example.post_project.web.dto.comment.commentRequestDto;
import com.example.post_project.web.dto.comment.commentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor // 생성자 DI 주입
@Service
public class CommentService extends BaseService<commentRequestDto, commentResponseDto, SessionUser, Comment> {
    private final PostRepository postRepository;

    @Override
    @Transactional
    public ResponseEntity<commentResponseDto> Create(commentRequestDto commentRequestDto, SessionUser sessionUser) {
        Post post = postRepository.findById(commentRequestDto.getPostId()).orElseThrow(() -> new IllegalArgumentException("해당 글이 노존재"));
        commentRequestDto.setPost(post);
        Comment comment = baseRepository.save(commentRequestDto.toEntity());
       return ResponseEntity.ok(new commentResponseDto());
    }

    @Override
    @Transactional
    public Long update(commentRequestDto commentRequestDto, SessionUser sessionUser) {
        return null;
    }

    @Override
    @Transactional
    public Long delete(Long id, SessionUser sessionUser) {
        return null;
    }
}
