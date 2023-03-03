package com.example.diary.diary.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
@AllArgsConstructor


public class Today  {
    private int id; //고유id
    private  String userId; //할 일을 등록한 회원의 식별자
    private String author;//작성자
    private String content;//내용
    private String emotion;//감정...이모티콘
    private String created_date; //등록시간


}
