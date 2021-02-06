package com.example.post_project.service;

import com.example.post_project.domain.comment.CommentRepository;
import com.example.post_project.domain.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor // 생성자 DI 주입
@Service
public class commentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public void  create()
    {

    }
    @Transactional
    public void  update()
    {

    }
    @Transactional
    public void  delete()
    {

    }
    @Transactional
    public void read()
    {

    }
}
