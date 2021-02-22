package com.example.post_project.web;

import com.example.post_project.config.auth.LoginUser;
import com.example.post_project.config.auth.dto.SessionUser;
import com.example.post_project.domain.Pagenation;
import com.example.post_project.domain.post.Post;
import com.example.post_project.service.postService;
import com.example.post_project.web.dto.post.PostListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class indexController {
    private final postService postService;
    @GetMapping("")
    public String index(@PageableDefault(size = 2,sort = "modified",direction = Sort.Direction.DESC) Pageable pageable, @RequestParam(value = "keyword",required = false)String keyword,
                        Model model , @LoginUser SessionUser user) {
        if(user!=null)
        {
            model.addAttribute("username",user.getName());
        }

       if(keyword==null)
       {
          Pagenation postList =postService.pagingList(pageable);
           model.addAttribute("post",postList.getPagingList());
           if(postList.getCurrentPage()!=0)
               model.addAttribute("prev",pageable.previousOrFirst().getPageNumber());
           System.out.println("pageable get PageNumber"+pageable.getPageNumber());
            System.out.println("postList.getTotalPages()"+postList.getTotalPages());
           if(postList.getTotalPages()-1>pageable.getPageNumber())
           {
               model.addAttribute("next",pageable.next().getPageNumber());
           }
           model.addAttribute("pageable", pageable);
       }
       else
       {

           List<PostListResponseDto> list=postService.Search(keyword,pageable);
           for(PostListResponseDto p:list)
           {
               System.out.println(p.toString());
           }
            long all=postService.usingSearchPagingCnt2(keyword);
           model.addAttribute("post",list);
           model.addAttribute("prev",pageable.previousOrFirst().getPageNumber());
           int pageSize=list.size();
           if(all/5>pageable.getPageNumber())
           {
               model.addAttribute("next",pageable.next().getPageNumber());
           }
           model.addAttribute("pageable", pageable);
       }
        return "index";
    }


    @GetMapping("/loginForm")
    public String loginForm()
    {
        return "loginForm";
    }


    @GetMapping("/signUpForm")
    public String signUp()
    {
        return "signUpForm";
    }
    @GetMapping("/jsEx")
    public String s(@RequestParam(name="t", required = false) String title){
        System.out.println(title);

        return "jsEx";
    }

    @GetMapping("/hw")
    public String ss()
    {
        return "hw";
    }


}
