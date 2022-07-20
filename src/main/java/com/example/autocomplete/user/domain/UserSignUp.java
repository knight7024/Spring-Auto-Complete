package com.example.autocomplete.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@RedisHash("user_search")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUp implements Serializable {
    private static final long serialVersionUID = -8044660328653751420L;

    @Size(min = 4, max = 30, message = "아이디는 4~30글자까지 입력할 수 있습니다.")
    protected String username;

    @Size(min = 8, max = 50, message = "비밀번호는 8~50글자까지 입력할 수 있습니다.")
    @Pattern(regexp = "[a-zA-Z0-9`~!@#$%^&*()-_=+,./?]+", message = "비밀번호에는 영문자, 숫자, 허용된 특수문자(`~!@#$%^&*()-_=+,./?)만 입력할 수 있습니다.")
    @JsonIgnore
    protected transient String password;

    @Size(min = 1, max = 30, message = "성은 1~30글자까지 입력할 수 있습니다.")
    @Pattern(regexp = "[가-힣a-zA-Z]+", message = "성은 영문자와 한글만 입력할 수 있습니다.")
    protected String firstName;

    @Size(min = 1, max = 30, message = "이름은 1~30글자까지 입력할 수 있습니다.")
    @Pattern(regexp = "[가-힣a-zA-Z]+", message = "이름은 영문자와 한글만 입력할 수 있습니다.")
    protected String lastName;
}
