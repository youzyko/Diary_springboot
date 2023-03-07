package com.example.diary.diary.service;

import com.example.diary.diary.dto.FindAllDTO;
import com.example.diary.diary.entity.Today;
import com.example.diary.diary.repository.TodayRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TodayService {
    private final TodayRepository repository;

/*  public List<Today> viewall(){ //전체항목
      log.info("전체항목 진입성공");
      List<Today> viewall=repository.viewall();
      return  viewall;
  }*/

    public FindAllDTO createServ(Today today){
        log.info("글등록_service");
        if(today.getAuthor()==""||today.getContent()==""){
            log.warn("Today는 null 일 수 없습니다");
            throw new RuntimeException("newTodo cannot be null!");
        }
        boolean flag=repository.save(today);
        if(flag)
            return flag ? findAllServ(today.getUserId()) : null;
        else
            return null;
    }
    public FindAllDTO findAllServ(String userId) {
        return new FindAllDTO(repository.findAll(userId));
    }



    public FindAllDTO deleteServ(int id, String userId){ //삭제
        log.info("일기 삭제service 진입 성공");
        boolean delete=repository.delete(id);
        if (!delete) {
            log.warn("delete fail!! not found id [{}]", id);
            throw new RuntimeException("delete fail!");
        }
        return findAllServ(userId);
    }


    public FindAllDTO update(Today today){ //수정
        log.info("일기 수정service 진입 성공");
        int numDelete = repository.modify(today);
        //log.info(String.valueOf(numDelete)); //몇개가 수정 됐는지 보기위함

        return findAllServ(today.getUserId());
    }



}//class end