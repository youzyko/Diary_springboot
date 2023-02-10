package com.example.diary.service;

import com.example.diary.entity.Today;
import com.example.diary.repository.TodayRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TodayService {
   private final TodayRepository repository;

  public List<Today> viewall(){ //전체항목
      log.info("전체항목 진입성공");
      List<Today> viewall=repository.viewall();
      return  viewall;
  }
  public Today createServ(final Today today){ //새로운 일기 생성
      log.info("새로운 일기 생성 진입");
      if(today==null){
          log.warn("일기에 내용이 없습니다 재확인요망");
          throw new RuntimeException("일기에 내용이 없습니다");
      }
      boolean flag=repository.save(today);  // 왜 boolean으로 처리..? 쓰지도 않음
      return today;

  }

  public List<Today> deleteServ(int id) {
    log.info("삭제 서비스에 진입했습니다,");
    boolean
  }
}//class end
