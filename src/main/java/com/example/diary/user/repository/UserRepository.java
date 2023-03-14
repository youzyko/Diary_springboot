package com.example.diary.user.repository;

import com.example.diary.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
    User findUserById(String id);

    // 회원 가입하기
    boolean register(User user);

    //이ㅔ일 중복확인
    boolean existsByEmail(String email);

    //프로필 사진 찾기
     String findProfile(String userId);

}//class_end
