package com.example.diary.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
//오늘의 일기

public class Today {
    private int id; //고유id
    private String author;//작성자
    private String content;//내용
    private int emotion;//감정 점수
    private Date created_date; //등록시간


    public void today(int id, String author, String content, int emotion, Date created_date) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.emotion = emotion;
        this.created_date = created_date;
    }
}
