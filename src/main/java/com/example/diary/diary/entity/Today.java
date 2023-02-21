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
    private String author;//작성자
    private String content;//내용
    private String emotion;//감정...이모티콘
    private String created_date; //등록시간


    public void today(int id, String author, String content, String emotion, String created_date) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.emotion = emotion;
        this.created_date = created_date;
    }
}
