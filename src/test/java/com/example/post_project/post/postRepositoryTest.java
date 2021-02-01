package com.example.post_project.post;


import com.example.post_project.domain.post.Post;
import com.example.post_project.domain.post.PostRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class postRepositoryTest {

    @Autowired
    PostRepository postRepository;
    @After
    public void cleanup()
    {
        postRepository.deleteAll();
    }

    @Test
    public void postSave()
    {
        String title="test title";
        String writer="test writer";
        String content="test content";
        Post p=Post.builder().title(title).writer(writer).content(content).build();
        postRepository.save(p);
        List<Post> list=postRepository.findAll();

        assertThat(list.get(0).getTitle()).isEqualTo(title);
        assertThat(list.get(0).getWriter()).isEqualTo(writer);
        assertThat(list.get(0).getContent()).isEqualTo(content);
    }


}
