package com.multi.service;

import com.multi.dao.EnrollmentDAO;
import com.multi.dto.Enrollment;

import java.util.List;

public class EnrollmentServiceImpl implements EnrollmentService {

    private EnrollmentDAO enrollmentDao = new EnrollmentDAO();

    @Override
    public void enroll(Long studentId, Long courseId) {
        Enrollment e = new Enrollment(studentId, courseId);
        enrollmentDao.insert(e);
    }

    @Override
    public void cancel(Long studentId, Long courseId) {
        enrollmentDao.delete(studentId, courseId);
    }

    @Override
    public List<Enrollment> getEnrollmentsByStudent(Long studentId) {
        return enrollmentDao.findByStudent(studentId);
    }

    @Override
    public List<Enrollment> getEnrollmentsByCourse(Long courseId) {
        return enrollmentDao.findByCourse(courseId);
    }
}
