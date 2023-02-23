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

    @PostMapping("/signup") //회원가입기능
    public ResponseEntity<?> register(@RequestPart ("userInfo") UserRequestDto reqDto)throws IOException {
        try{
            User user=new User(reqDto)

        }catch (){

        }
    }//signup_end


    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody UserRequestDto dto) { //로그인
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






}
