package com.example.post_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //mappedSuper 활성
@SpringBootApplication
public class PostProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostProjectApplication.class, args);
    }

}
