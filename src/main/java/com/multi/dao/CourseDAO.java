package com.multi.dao;

import com.multi.dto.Course;
import com.multi.mapper.CourseMapper;
import com.multi.util.SqlSessionFactoryProvider;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * CourseDAO
 * - MyBatis SqlSession을 이용해 DB 접근을 담당
 * - SQL 실행 후 결과를 DTO로 반환
 */

public class CourseDAO {
    public List<Course> findAll(String keyword, int page, int size){ // 목록 조회 (검색 + 페이징)
        int p = Math.max(1, page);
        int s = Math.max(1, size);
        int offset = (p - 1) * s;

        Map<String,Object> params = new HashMap<>();
        params.put("keyword", keyword);
        params.put("size", s);
        params.put("offset", offset);

        try(SqlSession session = SqlSessionFactoryProvider.getFactory().openSession()){
            return session.getMapper(CourseMapper.class).findAll(params);
        }
    }

    public int countAll(String keyword){
        Map<String,Object> params = new HashMap<>();
        params.put("keyword", keyword);
        try(SqlSession session = SqlSessionFactoryProvider.getFactory().openSession()){
            return session.getMapper(CourseMapper.class).countAll(params);
        }
    }

    public Course findById(Long id){
        try(SqlSession session = SqlSessionFactoryProvider.getFactory().openSession()){
            return session.getMapper(CourseMapper.class).findById(id);
        }
    }

    public Course findByTitle(String title){
        try(SqlSession session = SqlSessionFactoryProvider.getFactory().openSession()){
            return session.getMapper(CourseMapper.class).findByTitle(title);
        }
    }

    public Course findByCode(String code){
        try(SqlSession session = SqlSessionFactoryProvider.getFactory().openSession()){
            return session.getMapper(CourseMapper.class).findByCode(code);
        }
    }

    public int insert(Course course){
        try(SqlSession session = SqlSessionFactoryProvider.getFactory().openSession()){
            int rows = session.getMapper(CourseMapper.class).insert(course);
            session.commit();
            return rows;
        }
    }

    public int update(Course course){
        try(SqlSession session = SqlSessionFactoryProvider.getFactory().openSession()){
            int rows = session.getMapper(CourseMapper.class).update(course);
            session.commit();
            return rows;
        }
    }

    public int delete(Long id){
        try(SqlSession session = SqlSessionFactoryProvider.getFactory().openSession()){
            int rows = session.getMapper(CourseMapper.class).delete(id);
            session.commit();
            return rows;
        }
    }
}
