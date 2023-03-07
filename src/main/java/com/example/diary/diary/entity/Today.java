package com.example.diary.diary.entity;


import com.sun.istack.NotNull;
import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@Builder

public class Today  {
    private int id; //고유id
    private  String userId; //할 일을 등록한 회원의 식별자
    @NotNull
    private String author;//작성자
    private String content;//내용
    private String emotion;//감정...이모티콘
    private String created_date; //등록시간



}
