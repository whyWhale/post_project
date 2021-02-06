package com.example.post_project.service;

import com.example.post_project.config.auth.LoginUser;
import com.example.post_project.config.auth.dto.SessionUser;
import com.example.post_project.domain.post.Post;
import com.example.post_project.domain.post.PostRepository;
import com.example.post_project.domain.user.Users;
import com.example.post_project.domain.user.UsersRepository;
import com.example.post_project.web.dto.post.PostListResponseDto;
import com.example.post_project.web.dto.post.PostResponseDto;
import com.example.post_project.web.dto.post.PostSaveRequestDto;
import com.example.post_project.web.dto.post.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

//import javax.transaction.Transactional; // option x.
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class postService {

    private final PostRepository postRepository;
    private final UsersRepository usersRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto, SessionUser sessionUser)
    {
        Users user=usersRepository.findById(sessionUser.getId()).get();
        requestDto.setUser(user);
        return postRepository.save(requestDto.entity()).getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto, @LoginUser SessionUser user) {
        Post post=postRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 글이 노존재"));
        if(post.getUser().getId().equals(user.getId()))
            post.update(requestDto); // dirtyCheck
        else
            return null;
        return id;
    }

    @Transactional
    public PostResponseDto findById(Long id) {
        Post post=postRepository.findById(id).orElseThrow(()->new IllegalArgumentException("노 게시판") );
        return new PostResponseDto(post);
    }
    @Transactional
    public void delete(Long id) {
        Post post=postRepository.findById(id).orElseThrow(()->new IllegalArgumentException("노 게시판") );
        postRepository.delete(post);
        System.out.println("Clean Remove ------------------------------ ");
    }

    @Transactional(readOnly = true)
    public List<PostListResponseDto> findAll()
    {
        return postRepository.findAllByDesc().stream().map(PostListResponseDto::new).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<PostListResponseDto> Search(String keyword,Pageable pageable)
    {
        return postRepository.findByTitleContainingOrContentContaining(keyword,keyword,pageable).stream().map(PostListResponseDto::new).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<Post> pagingList(Pageable pageable)
    {
        return postRepository.findAll(pageable).toList();
    }

    @Transactional(readOnly = true)
    public long usingAllPagingCnt()
    {
        return postRepository.count();
    }
    @Transactional(readOnly = true)
    public long usingSearchPagingCnt2(String keyword)
    {
        return postRepository.findByTitleContainingOrContentContaining(keyword,keyword).size();
    }
}
