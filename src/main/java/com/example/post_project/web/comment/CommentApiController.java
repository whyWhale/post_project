package com.example.post_project.web.comment;

import com.example.post_project.config.auth.dto.SessionUser;
import com.example.post_project.domain.comment.Comment;
import com.example.post_project.web.CRUD_Controller;
import com.example.post_project.web.dto.comment.commentRequestDto;
import com.example.post_project.web.dto.comment.commentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/comment/api")
@RequiredArgsConstructor
@RestController
public class CommentApiController extends CRUD_Controller<commentRequestDto, commentResponseDto, SessionUser, Comment> {


}
