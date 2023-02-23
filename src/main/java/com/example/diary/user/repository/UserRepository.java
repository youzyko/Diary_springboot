package com.example.diary.user.repository;

import com.example.diary.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
    User findUserById(String id);
}
