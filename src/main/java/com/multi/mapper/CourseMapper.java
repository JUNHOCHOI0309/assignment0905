package com.multi.mapper;

import com.multi.dto.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/*
 * CourseMapper
 * - MyBatis Mapper 인터페이스
 * - CourseMapper.xml에 정의된 SQL을 자바 메서드처럼 호출
 */

public interface CourseMapper {
    int countAll(Map<String,Object> params); // 전체 강좌 수 조회 (검색 조건 적용)
    List<Course> findAll(Map<String,Object> params); // 강좌 목록 조회 (검색 + 페이징)
    Course findById(@Param("id") Long id);
    Course findByTitle(@Param("title") String title);
    Course findByCode(@Param("code") String code);
    int insert(Course course);  // 새 강좌 등록
    int update(Course course); // 강좌 수정
    int delete(@Param("id") long id); // 강좌 삭제
}
