package com.example.diary.user.service;

import com.example.diary.user.entity.User;
import com.example.diary.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
   private final UserRepository userRepository;

    //로그인
    public User validateLogin(String id,String pwd){
        User user=
    }


}//class end
