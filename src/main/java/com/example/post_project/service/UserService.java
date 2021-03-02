package com.example.post_project.service;

import com.example.post_project.HttpStatusResponse;
import com.example.post_project.config.auth.dto.SessionUser;
import com.example.post_project.domain.user.Users;
import com.example.post_project.domain.user.UsersRepository;
import com.example.post_project.web.dto.user.UserRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Optional;
@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UsersRepository usersRepository;
    private final HttpSession httpSession;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("username = " + email);
        Optional<Users> user=usersRepository.findByEmail(email);
        if(user.isPresent())
        {
            httpSession.setAttribute("user", new SessionUser(user.get()));
            log.info(user.get().getPassword());
            return user.get();
        }
        log.info(" this "+email+" not signUp email ,check you Email!");
        throw new UsernameNotFoundException(" this "+email+" not signUp email ,check you Email!");
    }

    @Transactional
    public ResponseEntity create(UserRequestDto userRequestDto)
    {
        boolean flag=usersRepository.existsByEmail(userRequestDto.getEmail());
        if(flag)
        {
            return HttpStatusResponse.RESPONSE_CONFLICT;
        }
        System.out.println(userRequestDto.toString());
        Users user=usersRepository.save(userRequestDto.toEntity());
        return ResponseEntity.ok().body("로그인이 완료되었습니다.");
    }


    public ResponseEntity checkEmail(String email) {
        boolean check = usersRepository.existsByEmail(email);
        if(check)
        {
            return HttpStatusResponse.RESPONSE_CONFLICT;
        }
        return ResponseEntity.ok("사용가능한 아이디 입니다.");
    }
}
