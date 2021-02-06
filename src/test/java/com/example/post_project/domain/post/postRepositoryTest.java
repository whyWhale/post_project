package com.example.post_project.domain.post;

import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class postRepositoryTest {
    @Autowired
    PostRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    @DisplayName("post import_check")
    public void post_import() {
        //given
        String title = "test title";
        String content = "test content";

        postsRepository.save(Post.builder()
                .title(title)
                .content("test title")
                .writer("KBY_TECH")
                .build());

        //when
        List<Post> postsList = postsRepository.findAll();

        //then
        Post posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
    @Test
    @DisplayName("keyword searching")
    public void search()
    {
        //given
        String title = "test title";
        String content = "test content";

        postsRepository.save(Post.builder()
                .title("x")
                .content(content)
                .writer("KBY_TECH")
                .build());
        postsRepository.save(Post.builder()
                .title(title)
                .content("x")
                .writer("KBY_TECH2")
                .build());
        postsRepository.save(Post.builder()
                .title(title)
                .content(content)
                .writer("KBY_TECH3")
                .build());

        //when
        List<Post> postsList = postsRepository.findByKeyword("x","x");
        for(Post p:postsList)
        {
            System.out.println(p.getTitle());
            System.out.println(p.getContent());
//            assertThat(p.getTitle()).isEqualTo(title);
//            assertThat(p.getContent()).isEqualTo(content);
        }
        //then
        if(!postsList.isEmpty()) {
            Post posts = postsList.get(0);
//            assertThat(posts.getTitle()).isEqualTo(title);
//            assertThat(posts.getContent()).isEqualTo(content);
        }
        else
            System.out.println("NULL@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

    @Test
    @DisplayName("BaseTimeEntity import_test")
    public void BaseTimeEntity_check() {
        //given
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        postsRepository.save(Post.builder()
                .title("title")
                .content("content")
                .writer("writer")
                .build());
        //when
        List<Post> postsList = postsRepository.findAll();

        //then
        Post posts = postsList.get(0);

        System.out.println(">>>>>>>>> createDate=" + posts.getFormattiedCreateDate()+ ", modifiedDate=" + posts.getFormattiedModifyDate());

        assertThat(posts.getFormattiedCreateDate()).isEqualTo(now);
        assertThat(posts.getFormattiedModifyDate()).isEqualTo(now);
    }
}
