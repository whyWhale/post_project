package com.example.post_project.web;

import com.example.post_project.config.auth.LoginUser;
import com.example.post_project.config.auth.dto.SessionUser;
import com.example.post_project.domain.post.Post;
import com.example.post_project.service.postService;
import com.example.post_project.web.dto.PostListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    public String index(@PageableDefault(size = 5,sort = "created") Pageable pageable,@RequestParam(value = "keyword",required = false)String keyword,
                         Model model , @LoginUser SessionUser user) {
        if(user!=null)
        {
            model.addAttribute("username",user.getEmail());
        }

       if(keyword==null)
       {
           List<Post> pageList=postService.pagingList(pageable);
           model.addAttribute("post",pageList);
           model.addAttribute("prev",pageable.previousOrFirst().getPageNumber());
           int pageSize=pageList.size();
           long all=postService.usingAllPagingCnt();
           if(all/5>pageable.getPageNumber())
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

    @GetMapping("/jsEx")
    public String s(){

        return "jsEx";
    }


}
