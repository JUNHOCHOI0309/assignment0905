package com.multi.dao;

import com.multi.dto.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDAO {
    List<Student> findAll(@Param("q") String q, @Param("limit") int limit, @Param("offset") int offset);
    long countAll(@Param("q") String q);
    Student findById(@Param("id") long id);
    int insert(Student student);
    int update(Student student);
    int delete(@Param("id") long id);
}
