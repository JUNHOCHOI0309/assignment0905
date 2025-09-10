package com.multi.service;

// service/EnrollmentService.java
import com.multi.dto.Enrollment;

import java.util.List;

public interface EnrollmentService {
    List<Enrollment> getEnrollmentsByStudent(Long studentId);
    List<Enrollment> getEnrollmentsByCourse(Long courseId);
    void enroll(Long studentId, Long courseId);
    void cancel(Long studentId, Long courseId);
}
