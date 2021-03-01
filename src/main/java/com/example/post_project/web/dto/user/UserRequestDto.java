package com.example.post_project.web.dto.user;

import com.example.post_project.domain.user.Role;
import com.example.post_project.domain.user.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRequestDto {
    @NotEmpty(message = "이름을 입력하세요.")
    @Pattern(regexp = "^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$\n",message = "시작은 영문으로만, '_'를 제외한 특수문자 안되며 영문, 숫자, '_'으로만 이루어진 5 ~ 12자 이하\n")
    private String name;

    @NotEmpty
    @Email(message = "이메일 형식을 올바르게 입력해주세요.")
    private String email;  // username

    @NotEmpty
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private String password;

    public void encrypt(PasswordEncoder passwordEncoder)
    {
        this.password=passwordEncoder.encode(this.password);
    }

    public Users toEntity()
    {
        return new Users(name,email,Role.USER,password);
    }

}
