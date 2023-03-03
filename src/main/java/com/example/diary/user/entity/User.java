package com.example.diary.user.entity;

import com.example.diary.user.dto.UserRequestDto;
import lombok.*;

@Getter
@Setter
@ToString

@AllArgsConstructor
public class User {
    private String id; //아이디
    private String username; //이름
    private String email; //이메일
    private String pwd; //비밀번호

    public User(UserRequestDto dto) {
        this.email = dto.getId();
        this.username = dto.getUsername();
        this.pwd = dto.getPwd();
    }


}
