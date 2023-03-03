package com.example.diary.diary.repository;
import com.example.diary.diary.entity.Today;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface TodayRepository {

    boolean save(Today today);



  int modify(Today today); //수정하기


  List<Today> findAll(String userId);

    boolean delete(int id);
}
