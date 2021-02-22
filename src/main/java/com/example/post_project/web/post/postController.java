package com.example.post_project.web.post;

import com.example.post_project.config.auth.LoginUser;
import com.example.post_project.config.auth.dto.SessionUser;
import com.example.post_project.service.postService;
import com.example.post_project.web.dto.post.PostResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/post")
public class postController {
    private final postService postService;
    @GetMapping("/save")
    public String savePage(@LoginUser SessionUser user,Model model)
    {
        if(user!=null)
        {
            model.addAttribute("username",user.getName());
        }
        return "/post/save";
    }

    @GetMapping("/update/{id}")
    public String updatePage(@PathVariable Long id,Model model,@LoginUser SessionUser user)
    {
        PostResponseDto post=postService.findById(id);
            if(!user.getId().equals(post.getUsers().getId()))
            {
               // 인가 받지 못한 사용자의 접근.
                return "/";
            }
        if(user!=null)
        {
            model.addAttribute("username",user.getName());
        }
        model.addAttribute("post",post);
        return "/post/update";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model,@LoginUser SessionUser sessionUser) {
        if(sessionUser!=null)
        {
            model.addAttribute("username",sessionUser.getName());
        }
        PostResponseDto post=postService.findById(id);
        log.info("게시물 작성자 ->{}",post);
        log.info("{}",sessionUser.getId());
        if(post.getUsers().getId().equals(sessionUser.getId()))
        {
            model.addAttribute("sessionUser",sessionUser);
        }
        else {
            System.out.println(post.getUsers().getId().equals(sessionUser.getId()));
        }
        model.addAttribute("post",post);

        return "/post/detail";
    }

}
