package com.example.diary.diary.dto;

import com.example.diary.diary.entity.Today;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DiaryDto {
    private int id; //고유id

    private String author;//작성자
    private String content;//내용
    private String emotion;//감정...이모티콘
    private String created_date; //등록시간

    //Today에서 TodoDto가 필요한 필드 뺴오기
    public DiaryDto(Today today) {
        this.id=today.getId();
        this.author=today.getAuthor();
        this.content=today.getContent();
        this.emotion=today.getEmotion();
        this.created_date=today.getCreated_date();
    }
}
