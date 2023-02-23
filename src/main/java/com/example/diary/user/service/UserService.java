package com.example.diary.user.service;

import com.example.diary.user.entity.User;
import com.example.diary.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private  final  UserRepository userRepository;
    private  final PasswordEncoder encoder;

    //로그인 검증 메서드
    public User validateLogin(final String id, final String pwd) {

        // 회원가입을 했는가?
        User user = getByCredential(id);

        if (user == null) throw new RuntimeException("가입된 회원이 아닙니다.");

        // 패스워드가 일치하는가?

        if (!encoder.matches(pwd, user.getPwd())) {
            throw new RuntimeException("비밀번호가 틀렸습니다.");
        }

        return user; // 로그인 성공시 회원정보 리턴
    }
   public User getByCredential (String id){
        return  userRepository.findUserById(id);
   }

}//class end
