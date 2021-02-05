package com.example.post_project.post;

import com.example.post_project.config.auth.dto.SessionUser;
import com.example.post_project.domain.post.Post;
import com.example.post_project.domain.post.PostRepository;
import com.example.post_project.domain.user.Role;
import com.example.post_project.domain.user.Users;
import com.example.post_project.web.dto.PostSaveRequestDto;
import com.example.post_project.web.dto.PostUpdateRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;

import java.security.Security;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class postApiTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private WebApplicationContext con;

    @Autowired
    MockMvc mockMvc;

    @Before
    public void setUp()
    {
        mockMvc= MockMvcBuilders.webAppContextSetup(con).apply(springSecurity()).build();
    }


    // when not mapping Success
    @Test
    @DisplayName("post_created")
    @WithMockUser(roles="USER")
    public void saveApi() throws Exception
    {
        String title="test title";
        String writer="test writer";
        String content="test content";

        PostSaveRequestDto requestDto=PostSaveRequestDto.builder().title(title).content(content).writer(writer).build();
        String url="http://localhost:"+port+"/post/api";

        ResponseEntity<Long> responseEntity=testRestTemplate.postForEntity(url,requestDto,Long.class);

        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Post> list=postRepository.findAll();
        assertThat(list.get(0).getTitle()).isEqualTo(title);
        assertThat(list.get(0).getWriter()).isEqualTo(writer);
        assertThat(list.get(0).getContent()).isEqualTo(content);
    }

    // when not mapping Success
    @Test
    @DisplayName("post_Update")
    @WithMockUser(roles="USER")
    public void voidUpdateApi() throws Exception{
        //given
        String title="test title";
        String writer="test writer";
        String content="test content";

        Post post=postRepository.save(Post.builder().title(title).content(content).writer(writer).build());

        Long updateId=post.getId();

        PostUpdateRequestDto requestDto= PostUpdateRequestDto.builder().title(title).content(content).build();

        String url="http://localhost:"+port+"/post/api"+updateId;
        //when
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

//        HttpEntity<PostUpdateRequestDto> requestDtoHttpEntity=new HttpEntity<>(requestDto);
//        ResponseEntity<Long> responseEntity=testRestTemplate.exchange(url, HttpMethod.PUT,requestDtoHttpEntity,Long.class);
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        //then
        List<Post> all=postRepository.findAll();
        assertThat(all.get(0)).isEqualTo(post);
    }
}
