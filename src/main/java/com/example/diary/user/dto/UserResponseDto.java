package com.example.diary.user.dto;

import com.example.diary.user.entity.User;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private  String id;
    private  String pwd;

    private String token; //인증토큰

    //User_entity에서 id,pwd 빼오기
    public UserResponseDto(User user){
        this.id=user.getId();
        this.pwd=user.getPwd();
    }
}
