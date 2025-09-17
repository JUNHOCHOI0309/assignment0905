package com.multi.dao;

import com.multi.dto.Enrollment;
import com.multi.util.SqlSessionFactoryProvider;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class EnrollmentDAO {  // ← DAO로 변경 (대문자)

    public int insert(Enrollment enrollment) {
        try (SqlSession session = SqlSessionFactoryProvider.getSqlSessionFactory().openSession(true)) {
            return session.insert("EnrollmentMapper.insert", enrollment);
        }
    }

    public int delete(Long studentId, Long courseId) {
        try (SqlSession session = SqlSessionFactoryProvider.getSqlSessionFactory().openSession(true)) {
            Enrollment param = new Enrollment(studentId, courseId);
            return session.delete("EnrollmentMapper.delete", param);
        }
    }

    public Enrollment findByStudentAndCourse(Long studentId, Long courseId) {
        try (SqlSession session = SqlSessionFactoryProvider.getSqlSessionFactory().openSession()) {
            Enrollment param = new Enrollment(studentId, courseId);
            return session.selectOne("EnrollmentMapper.findByStudentAndCourse", param);
        }
    }

    public List<Enrollment> findByStudent(Long studentId) {
        try (SqlSession session = SqlSessionFactoryProvider.getSqlSessionFactory().openSession()) {
            return session.selectList("EnrollmentMapper.findByStudent", studentId);
        }
    }

    public List<Enrollment> findByCourse(Long courseId) {
        try (SqlSession session = SqlSessionFactoryProvider.getSqlSessionFactory().openSession()) {
            return session.selectList("EnrollmentMapper.findByCourse", courseId);
        }
    }
}
