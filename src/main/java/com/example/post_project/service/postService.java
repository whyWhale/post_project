package com.example.post_project.service;

import com.example.post_project.config.auth.dto.SessionUser;
import com.example.post_project.domain.post.Post;
import com.example.post_project.domain.post.PostRepository;
import com.example.post_project.domain.user.Users;
import com.example.post_project.domain.user.UsersRepository;
import com.example.post_project.web.dto.PostListResponseDto;
import com.example.post_project.web.dto.PostResponseDto;
import com.example.post_project.web.dto.PostSaveRequestDto;
import com.example.post_project.web.dto.PostUpdateRequestDto;
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
        Long result=postRepository.save(requestDto.entity()).getId();
        return result;
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto) {
        Post post=postRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 글이 노존재"));
        post.update(requestDto); // dirtyCheck
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
        return postRepository.findByTitleOrContentContaining(keyword,keyword,pageable).stream().map(PostListResponseDto::new).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<Post> pagingList(Pageable pageable)
    {
        return postRepository.findAll(pageable).toList();
    }

    @Transactional(readOnly = true)
    public long useingAllPagicCnt()
    {
        return postRepository.count();
    }
    @Transactional(readOnly = true)
    public long useingSearchPagingCnt2(String keyword)
    {
        return postRepository.findByTitleOrContentContaining(keyword,keyword).size();
    }
}
