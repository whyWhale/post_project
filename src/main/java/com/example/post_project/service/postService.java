package com.example.post_project.service;

import com.example.post_project.config.auth.LoginUser;
import com.example.post_project.config.auth.dto.SessionUser;
import com.example.post_project.domain.Pagenation;
import com.example.post_project.domain.post.Post;
import com.example.post_project.domain.post.PostRepository;
import com.example.post_project.domain.user.Users;
import com.example.post_project.domain.user.UsersRepository;
import com.example.post_project.web.dto.post.PostListResponseDto;
import com.example.post_project.web.dto.post.PostResponseDto;
import com.example.post_project.web.dto.post.PostSaveRequestDto;
import com.example.post_project.web.dto.post.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

//import javax.transaction.Transactional; // option x.
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@RequiredArgsConstructor
@Service
public class postService {

    private final PostRepository postRepository;
    private final UsersRepository usersRepository;

    @Transactional
    public ResponseEntity save(PostSaveRequestDto requestDto, SessionUser sessionUser)
    {
        Users user=usersRepository.findById(sessionUser.getId()).get();
        requestDto.setUser(user);
        Post create=postRepository.save(requestDto.entity());
        log.info("ResponseEntity : {}", ResponseEntity.ok(new PostResponseDto(create)));
        return ResponseEntity.ok("게시글 작성.");
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto, @LoginUser SessionUser user) {
        Post post=postRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 글이 노존재"));
        if(post.getUser().getId().equals(user.getId()))
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
        ResponseEntity.ok(new PostResponseDto());
        log.info("삭제번호" +post);
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
    public Pagenation pagingList(Pageable pageable)
    {
        Page<Post> all=postRepository.findAll(pageable);
        Pagenation pagenation=Pagenation.builder()
                .totalPages(all.getTotalPages()) // 페이지 개수 0부터
                .totalElements(all.getTotalElements()) // 총 post 원소 갯수
                .currentPage(all.getNumber()) // 현재 페이지
                .currentElements(all.getNumberOfElements()) // 현재 페에지의 개수.
                .pagingList(all.stream().map(post -> post).collect(Collectors.toList()))
                .build();
        return pagenation;
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
