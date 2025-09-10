package com.multi.service;

import com.multi.dao.CourseDAO;

import java.util.List;

/*
 * CourseServiceImpl
 * - CourseService 인터페이스 구현체
 * - 내부적으로 CourseDAO 호출
 */

public class CourseServiceImpl implements CourseService {
    private final CourseDAO dao = new CourseDAO();

    @Override
    public List<com.multi.dto.Course> findAll(String keyword, int page, int size) {
        return dao.findAll(keyword, page, size);
    }

    @Override
    public int countAll(String keyword) {
        return dao.countAll(keyword);    }

    @Override
    public com.multi.dto.Course findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public com.multi.dto.Course findByTitle(String title) {
        return dao.findByTitle(title);    }

    @Override
    public com.multi.dto.Course findByCode(String code) {
        return dao.findByCode(code);    }

    @Override
    public void insert(com.multi.dto.Course course) {
        dao.insert(course);
    }

    @Override
    public void update(com.multi.dto.Course course) {
        dao.update(course);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }
}
