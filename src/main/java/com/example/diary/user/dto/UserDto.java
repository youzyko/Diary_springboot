package com.example.diary.user.dto;

import com.example.diary.user.entity.User;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private  String id;
    private  String pwd;

    //User_entity에서 id,pwd 빼오기
    public UserDto(User user){
        this.id=user.getId();
        this.pwd=user.getPwd();
    }
}
