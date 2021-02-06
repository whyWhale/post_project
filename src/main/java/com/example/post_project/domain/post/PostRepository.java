package com.example.post_project.domain.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    @Query(value = "select p from Post p order by p.created desc ")
    List<Post> findAllByDesc();

    Page<Post> findAll(Pageable pageable);

    List<Post> findByTitleContainingOrContentContaining(String title,String content,Pageable pageable);
    List<Post> findByTitleContainingOrContentContaining(String title,String content);

    @Query(value = "select p from Post p where p.title like %:title% or p.content like %:content% ")
    List<Post> findByKeyword(@Param("title")String title, @Param("content")String content);

}
