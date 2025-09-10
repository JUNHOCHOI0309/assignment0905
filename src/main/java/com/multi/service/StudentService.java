package com.multi.service;

import com.multi.dto.PageRequest;
import com.multi.dto.PageResult;
import com.multi.dto.Student;

public interface StudentService {
    PageResult<Student> list(String q, PageRequest pageRequest);
    Student get(long id);
    long create(Student student);
    void update(Student student);
    void delete(long id);
}
