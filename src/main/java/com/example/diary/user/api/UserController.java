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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<?> register(@RequestPart("userInfo") User user) {
        log.info("/auth/signup POST!! - login info : {}", user);
        try {
            User user1=userService.createServ(user);
            return  ResponseEntity.ok().body(new UserResponseDto(user));

        }
        catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }
/*
    @PostMapping("/signup")
    public ResponseEntity<?> register(
            @RequestPart("userInfo") UserRequestDto reqDto

    ) throws IOException {

        try {
            // userReqDto를 서비스에 전송
            // userEntity로 변환
            User entity = new User(reqDto);
            log.info("/auth/signup POST!! - userInfo : {}", entity);
            User user = userService.createServ(entity);


            return ResponseEntity.ok().body(new UserResponseDto(user));

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }*/

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
