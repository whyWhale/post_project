package com.example.post_project.domain.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    @Query(value = "select p from Post p order by p.id desc ")
    List<Post> findAllByDesc();

    Page<Post> findAll(Pageable pageable);

    List<Post> findByTitleOrContentContaining(String title,String content,Pageable pageable);
    List<Post> findByTitleOrContentContaining(String title,String content);

}
