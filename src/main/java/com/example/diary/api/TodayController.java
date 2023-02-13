package com.example.diary.api;

import com.example.diary.entity.Today;
import com.example.diary.service.TodayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?>listAll(){
        log.info("전체항목 불러오기");
        List<Today> viewall=service.viewall();
        return  ResponseEntity.ok().body(viewall);
    } //listAll_end

    @PostMapping //일기등록 요청
    public ResponseEntity<?> create(@RequestBody Today today){
        log.info("일기 등록 요청-controller");
        try {
            Today today1=service.createServ(today);
            if (today1==null){
                return ResponseEntity.notFound().build();
            }else {
                return ResponseEntity.ok().body(today1);
            }
        }catch (RuntimeException e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }// create_end

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        log.info("삭제 요청-controller");
        try {
            List<Today> today=service.deleteServ(id);
            if(today==null){
                return  ResponseEntity.notFound().build();
            }else{
                return  ResponseEntity.ok().body(today);
            }
        }catch (RuntimeException e){
            return  ResponseEntity.ok().body(e.getMessage());
        }
    }//delete_end

    @PutMapping //수정
  //아이디를 받아서 아이디에 해당하는 content를
    public  ResponseEntity<?> update(@RequestBody Today today){
       log.info("update_controller에 진입하셨습니다.");
      try {
          List<Today> todays = service.update(today);
          return ResponseEntity.ok().body(todays);
      }catch (Exception e){
          return ResponseEntity.notFound().build();
      }
    } //update end

}//TodayController_class_end
