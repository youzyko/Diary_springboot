package com.example.diary.diary.dto;

import com.example.diary.diary.entity.Today;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FindAllDTO {
    private int count; // 할 일 목록의 개수
    private List<DiaryDto> diarys; // userId가 빠진 할일 목록

    public FindAllDTO(List<Today> toDoList) {
        this.count = toDoList.size();
        this.convertDtoList(toDoList);
    }

    //List<Today>를 받으면 List<DiaryDto>로 변환
    public void convertDtoList(List<Today> toDoList){
        List<DiaryDto> dtos = new ArrayList<>();
        for (Today today:toDoList){
            dtos.add(new DiaryDto(today));
        }
        this.diarys=dtos;
    }
}//class end
