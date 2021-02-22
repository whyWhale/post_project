package com.example.post_project.config.auth;

import com.example.post_project.domain.user.Role;
import com.example.post_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final UserService userService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // http 보안 구성 메서드
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/hw","/loginForm","/jsEx", "/css/**", "/images/**",
                        "/js/**", "/h2-console/**","/user/api/**").permitAll()
                .antMatchers("/post/api/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()
                .and()
                .formLogin()//로그인페이지 사용
                .loginPage("/loginForm")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);

    }
    @Bean
    public PasswordEncoder encoder()
    {
        return new BCryptPasswordEncoder();
    }
    // 사용자 인증 정보 구성 메서드.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(encoder());
    }
}
