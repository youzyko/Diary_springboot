package com.example.diary.user.api;

import com.example.diary.error.ErrorDTO;
import com.example.diary.security.TokenProvider;

import com.example.diary.user.dto.UserRequestDto;
import com.example.diary.user.dto.UserResponseDto;
import com.example.diary.user.entity.User;
import com.example.diary.user.service.UserService;
import com.example.diary.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.FileCopyUtils;
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
    @Value("${upload.path}")
    private String uploadRootPath;

    //회원가입+로그인 후 사진 보이기

    //회원 가입... 사진 기능 추가
 @PostMapping("/signup")
    public ResponseEntity<?> register(
            @RequestPart("userInfo") User user
            , @RequestPart(value="profileImg", required = false) MultipartFile profileImg)
            throws IOException{
        try {
            if (profileImg != null) {
                log.info("profileImg : {}", profileImg.getOriginalFilename());

             //서버에 이미지 저장

                String originalFilename = profileImg.getOriginalFilename();

                // 1-a-1. 파일명이 중복되지 않도록 변경
                String uploadFileName = UUID.randomUUID() + "_" + originalFilename;

                // 1-a-2. 업로드 폴더를 날짜별로 생성
                String newUploadPath = FileUploadUtil.makeUploadDirectory(uploadRootPath);

                File uploadFile = new File(newUploadPath + File.separator + uploadFileName);

                // 1-b. 파일을 해당 경로에 업로드
                profileImg.transferTo(uploadFile);

                // 2. 데이터베이스에 이미지 정보를 저장 - 누가 어떤사진을 올렸는가

                // 2-a. newUploadPath에서 rootPath를 제거
                //  ex) new: E:/profile_upload/2023/01/07
                //      root: E:/profile_upload
                //      new - root == /2023/01/07

                // str: hello java
                // str.substring(6) => 6번부터 끝까지 추출 == java
                String savePath
                        = newUploadPath.substring(uploadRootPath.length());

            user.setProfileImg(savePath + File.separator + uploadFileName);
            }
            User user1 = userService.createServ(user);
      return  ResponseEntity.ok().body(new UserResponseDto(user1));
        }
        catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }



    //프로필찾기
    @GetMapping("/load-profile")
    public ResponseEntity<?> loadProfile(@AuthenticationPrincipal String userId) throws  IOException{
        log.info("/auth/load-profile GET - userId: {}", userId);

        // ex)  /2023/01/07/djfksjdkfsjf_파일명.확장자
        String profilePath = userService.getProfilePath(userId);

        // ex) E:/upload/2023/~~
        String fullPath = uploadRootPath + File.separator + profilePath;

        // 해당 경로를 파일 객체로 포장
        File targetFile = new File(fullPath);

        //해당 파일 존재x
        if(!targetFile.exists()) return  ResponseEntity.notFound().build();

        // 파일 데이터를 바이트배열로 포장 (blob 데이터)...대상 파일을 복사하여 Byte 배열로 반환해주는 클래스
        byte[] rawImageData = FileCopyUtils.copyToByteArray(targetFile);

        // 응답 헤더 정보 추가
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(FileUploadUtil.getMediaType(profilePath));

        // 클라이언트에 순수 이미지파일 데이터 리턴
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(rawImageData);


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


}//class_end
