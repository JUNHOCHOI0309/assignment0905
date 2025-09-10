package com.multi.service;

import com.multi.dao.EnrollmentDAO;
import com.multi.dto.Enrollment;
import com.multi.exception.DuplicateEnrollmentException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor   // final 필드에 대해 생성자 자동 생성
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentDAO enrollmentDAO;

    @Override
    public List<Enrollment> getEnrollmentsByStudent(Long studentId) {
        return enrollmentDAO.findByStudentId(studentId);
    }

    @Override
    public List<Enrollment> getEnrollmentsByCourse(Long courseId) {
        return enrollmentDAO.findByCourseId(courseId);
    }

    @Override
    public void enroll(Long studentId, Long courseId) {
        if (enrollmentDAO.exists(studentId, courseId)) {
            throw new DuplicateEnrollmentException("이미 수강신청된 과목입니다.");
        }
        Enrollment e = new Enrollment();
        e.setStudentId(studentId);
        e.setCourseId(courseId);
        enrollmentDAO.insert(e);
    }

    @Override
    public void cancel(Long studentId, Long courseId) {
        enrollmentDAO.delete(studentId, courseId);
    }
}
