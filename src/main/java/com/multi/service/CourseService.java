package com.multi.service;

import java.util.List;

/*
 * CourseService
 * - Controller와 DAO 사이의 중간 계층
 * - 비즈니스 로직을 담당 (검증, 예외 처리 등 추가 가능)
 */

public interface CourseService {
    List<com.multi.dto.Course> findAll(String keyword, int page, int size);
    int countAll(String keyword);
    com.multi.dto.Course findById(Long id);
    com.multi.dto.Course findByTitle(String title);
    com.multi.dto.Course findByCode(String code);
    void insert(com.multi.dto.Course course);
    void update(com.multi.dto.Course course);
    void delete(Long id);

}
