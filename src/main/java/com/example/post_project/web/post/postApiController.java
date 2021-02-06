package com.example.post_project.web.post;

import com.example.post_project.config.auth.LoginUser;
import com.example.post_project.config.auth.dto.SessionUser;
import com.example.post_project.service.*;
import com.example.post_project.web.dto.post.PostSaveRequestDto;
import com.example.post_project.web.dto.post.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/post/api")
@RestController
public class postApiController {
    private final postService postService;

    @PostMapping("")
    public Long postSave(@RequestBody PostSaveRequestDto requestDto, @LoginUser SessionUser user) {
        return postService.save(requestDto,user);
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
