package com.example.post_project.web.user;

import com.example.post_project.service.UserService;
import com.example.post_project.web.CRUD_Controller;
import com.example.post_project.web.dto.user.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/user/api")
@RequiredArgsConstructor
@RestController
public class userApiController{
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("")
    public ResponseEntity create(@Valid @RequestBody UserRequestDto userRequestDto) {
        userRequestDto.encrypt(passwordEncoder);
        return userService.create(userRequestDto);
    }

    @PostMapping("/mailCheck")
    public ResponseEntity duplicateEmailChecking(@RequestParam(value = "email") String email)
    {
        return userService.checkEmail(email);
    }

}

