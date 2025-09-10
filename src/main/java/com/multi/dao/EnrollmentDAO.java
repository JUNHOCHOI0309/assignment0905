package com.multi.dao;


import com.multi.dto.Enrollment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EnrollmentDAO {
    List<Enrollment> findByStudentId(Long studentId);
    List<Enrollment> findByCourseId(Long courseId);
    boolean exists(@Param("studentId") Long studentId, @Param("courseId") Long courseId);
    void insert(Enrollment enrollment);
    void delete(@Param("studentId") Long studentId, @Param("courseId") Long courseId);
}