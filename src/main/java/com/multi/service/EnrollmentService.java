package com.multi.service;

import com.multi.dto.Enrollment;

import java.util.List;

public interface EnrollmentService {
    void enroll(Long studentId, Long courseId);
    void cancel(Long studentId, Long courseId);
    List<Enrollment> getEnrollmentsByStudent(Long studentId);
    List<Enrollment> getEnrollmentsByCourse(Long courseId);
}
