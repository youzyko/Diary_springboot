package com.example.diary.user.api;

import com.example.diary.user.dto.UserDto;
import com.example.diary.user.entity.User;
import com.example.diary.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;

    @PostMapping  //로그인 기능
    public ResponseEntity<?> login(@RequestBody UserDto userDto){
        log.info("로그인 기능 요청_Controller");
        try {
            //List<User> user=userService.createUserService(userDto);
           User user=userService.validateLogin(userDto.getId(),userDto.getPwd());
           if (user!=null){
               return ResponseEntity.ok().body(user);
           }

        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    } // login_post




}
