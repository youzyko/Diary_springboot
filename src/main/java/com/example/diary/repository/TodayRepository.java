package com.example.diary.repository;
import com.example.diary.entity.Today;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface TodayRepository {
  List<Today> viewall(); //전체보기

  boolean save(Today today); //새글쓰기

  boolean delete(int id); //삭제하기

  int modify(Today today); //수정하기



}
