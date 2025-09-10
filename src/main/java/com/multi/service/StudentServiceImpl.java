package com.multi.service;


import com.multi.dao.StudentImpl;
import com.multi.dto.PageRequest;
import com.multi.dto.PageResult;
import com.multi.dto.Student;

import java.util.List;

public class StudentServiceImpl implements StudentService{
    private final StudentImpl studentDAO = new StudentImpl();

    @Override
    public PageResult<Student> list(String q, PageRequest pageRequest) {
        List<Student> students = studentDAO.findAll(q, pageRequest.getSize(), pageRequest.getOffset());
        long totalCount = studentDAO.countAll(q);
        return new PageResult<>(students, pageRequest.getPage(), pageRequest.getSize(), totalCount);
    }

    @Override
    public Student get(long id) {
        Student student = studentDAO.findById(id);
        if(student == null){
            throw new IllegalArgumentException("No student found for id: " + id);
        }
        return student;
    }

    @Override
    public long create(Student student) {
        int rows = studentDAO.insert(student);
        if(rows == 0){
            throw new IllegalStateException("Failed to insert student");
        }
        return student.getId();
    }

    @Override
    public void update(Student student) {
        int rows = studentDAO.update(student);
        if(rows == 0){
            throw new IllegalStateException("Failed to update student");
        }
    }

    @Override
    public void delete(long id) {
        int rows = studentDAO.delete(id);
        if(rows == 0){
            throw new IllegalStateException("Failed to delete student");
        }
    }
}
