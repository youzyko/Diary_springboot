package com.example.diary.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class User {
    private String id; //아이디
    private String userName; //이름
    private String email; //이메일
    private String pwd; //비밀번호



}
