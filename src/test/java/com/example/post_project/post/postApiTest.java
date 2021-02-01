package com.example.post_project.post;

import com.example.post_project.domain.post.Post;
import com.example.post_project.domain.post.PostRepository;
import com.example.post_project.web.dto.PostSaveRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class postApiTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;
    @Autowired
    private PostRepository postRepository;

    @Test
    public void saveApi() throws Exception
    {
        String title="test title";
        String writer="test writer";
        String content="test content";
        PostSaveRequestDto requestDto=PostSaveRequestDto.builder().title(title).content(content).writer(writer).build();
        String url="http://localhost:"+port+"/post";

        ResponseEntity<Long> responseEntity=testRestTemplate.postForEntity(url,requestDto,Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Post> list=postRepository.findAll();
        assertThat(list.get(0).getTitle()).isEqualTo(title);
        assertThat(list.get(0).getWriter()).isEqualTo(writer);
        assertThat(list.get(0).getContent()).isEqualTo(content);
    }
}
