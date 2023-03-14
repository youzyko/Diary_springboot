package com.example.diary.user.entity;

import com.example.diary.user.dto.UserRequestDto;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString

@AllArgsConstructor
public class User {
    private String id; //아이디
    private String username; //이름
    private String email; //이메일
    private String pwd; //비밀번호

    private String profileImg;// 사진추가기능

    public User() {
        this.id= UUID.randomUUID().toString(); //중복 방지
    }
    public User(UserRequestDto dto) {
        this();
        this.id = dto.getId();
      /*  this.username = dto.getUsername();*/
        this.pwd = dto.getPwd();
    }


}
