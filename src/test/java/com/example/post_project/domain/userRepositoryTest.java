package com.example.post_project.domain;


import com.example.post_project.domain.user.Role;
import com.example.post_project.domain.user.Users;
import com.example.post_project.domain.user.UsersRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class userRepositoryTest {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원 가입 사용자")
    public void signUpTest() {
        Users newUser = new Users("kim", "kimby@naver.com", Role.USER, passwordEncoder.encode("1234"));
        Users users = usersRepository.save(newUser);
        assertThat(users).isNotNull();
        assertThat(users).isEqualTo(newUser);
    }

    @Test
    @DisplayName("사용자 중복 ")
    public void duplicateEmailTest() {
        signUpTest();
        Users newUser = new Users("qw", "kimby@naver.com", Role.USER, passwordEncoder.encode("22"));
        Optional<Users> byEmail = usersRepository.findByEmail(newUser.getEmail());
        if (byEmail.isPresent()) {
            System.out.println("중복된 아이디.");
            assertThat(byEmail).isNotEqualTo(newUser);
        } else {
            System.out.println("중복되지 않은 아이디");
            assertThat(byEmail).isNull();
        }
    }
}