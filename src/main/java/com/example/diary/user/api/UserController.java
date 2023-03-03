package com.example.diary.user.api;

import com.example.diary.error.ErrorDTO;
import com.example.diary.security.TokenProvider;

import com.example.diary.user.dto.UserRequestDto;
import com.example.diary.user.dto.UserResponseDto;
import com.example.diary.user.entity.User;
import com.example.diary.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;
    private final TokenProvider provider;

    //회원 가입
    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody User user) {
        log.info("/auth/signup POST!! - login info : {}", user);
        try {
            User user1=userService.createServ(user);
            return  ResponseEntity.ok().body(new UserResponseDto(user));

        }
        catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    //로그인
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody UserRequestDto dto) {
        log.info("/auth/signin POST!! - login info : {}", dto);

        try {
            User user
                    = userService.validateLogin(dto.getId(), dto.getPwd());

            // 토큰 생성
            final String token = provider.create(user);

            UserResponseDto responseDTO = new UserResponseDto(user);
            responseDTO.setToken(token); // 발행한 토큰을 응답정보에 포함

            return ResponseEntity.ok().body(responseDTO);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    } //signin_end

    //이메일 중복확인
    @GetMapping("/check")
    public ResponseEntity<?> checkEmail(String email) {
        boolean flag = userService.isDuplicate(email);
        log.info("{} 중복여부?? - {}", email, flag);
        return ResponseEntity.ok().body(flag);
    }





}//class_end
