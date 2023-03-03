package com.example.diary.diary.api;

import com.example.diary.diary.dto.FindAllDTO;
import com.example.diary.diary.entity.Today;
import com.example.diary.diary.service.TodayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/diary")
@RequiredArgsConstructor
@CrossOrigin
public class TodayController {
    private final TodayService service;

    @GetMapping //전체항목 불러오기
    public ResponseEntity<?>listAll(@AuthenticationPrincipal String userId){
        log.info("전체항목 불러오기");
        return  ResponseEntity.ok().body(service.findAllServ(userId));
    } //listAll_end



  @PostMapping//글등록
    public ResponseEntity<?> create ( @AuthenticationPrincipal String userId,@RequestBody Today today){
        log.info("글등록_controller_post");
        today.setUserId(userId);

        try {
            FindAllDTO dto = service.createServ(today);
            if(dto==null){
                return  ResponseEntity.notFound().build();
            }else{
                return  ResponseEntity.ok().body(dto);
            }
        }catch (RuntimeException e){
            return  ResponseEntity.ok().body(e.getMessage());
        }


    }

  @DeleteMapping("/{id}")  //삭제
    public ResponseEntity<?> delete(@PathVariable int id,@AuthenticationPrincipal String userId){
        log.info("삭제 요청-controller");
        try {
            FindAllDTO dtos = service.deleteServ(id,userId);
            return ResponseEntity.ok().body(dtos);
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }//delete_end

 @PutMapping //수정
  //아이디를 받아서 아이디에 해당하는 content를
    public  ResponseEntity<?> update(@RequestBody Today today, @AuthenticationPrincipal String userId){
       log.info("update_controller에 진입하셨습니다.");
       today.setUserId(userId);
      try {
          FindAllDTO dtos = service.update(today);
          return ResponseEntity.ok().body(dtos);
      }catch (Exception e){
          return ResponseEntity.notFound().build();
      }
    } //update end



}//TodayController_class_end
