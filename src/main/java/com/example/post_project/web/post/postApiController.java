package com.example.post_project.web.post;

import com.example.post_project.config.auth.LoginUser;
import com.example.post_project.config.auth.dto.SessionUser;
import com.example.post_project.service.postService;
import com.example.post_project.web.dto.post.PostSaveRequestDto;
import com.example.post_project.web.dto.post.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Validated
@RequiredArgsConstructor
@RequestMapping("/post/api")
@RestController
public class postApiController {
    private final postService postService;

    @PostMapping("")
    public ResponseEntity postSave(@RequestBody @Valid PostSaveRequestDto requestDto, @LoginUser SessionUser user, Errors error) {
        return  postService.save(requestDto,user);
    }

    @PutMapping("/{id}")
    public Long postUpdate(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto,@LoginUser SessionUser user) {
        return postService.update(id, requestDto,user);
    }

    @DeleteMapping("/{id}")
    public Long postDelete(@PathVariable Long id) {
        postService.delete(id);
        return id;
    }

}
