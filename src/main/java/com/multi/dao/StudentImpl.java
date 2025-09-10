package com.multi.dao;

import com.multi.dto.Student;

import com.multi.util.SqlSessionFactoryProvider;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class StudentImpl implements StudentDAO{
    @Override
    public List<Student> findAll(String q, int limit, int offset) {
        try(SqlSession session = SqlSessionFactoryProvider.getSqlSessionFactory().openSession()){
            return session.getMapper(StudentDAO.class).findAll(q, limit, offset);
        }
    }

    @Override
    public long countAll(String q) {
        try(SqlSession session = SqlSessionFactoryProvider.getSqlSessionFactory().openSession()){
            return session.getMapper(StudentDAO.class).countAll(q);
        }
    }

    @Override
    public Student findById(long id) {
        try(SqlSession session = SqlSessionFactoryProvider.getSqlSessionFactory().openSession()){
            return session.getMapper(StudentDAO.class).findById(id);
        }
    }

    @Override
    public int insert(Student student) {
        try(SqlSession session = SqlSessionFactoryProvider.getSqlSessionFactory().openSession()){
            int result = session.getMapper(StudentDAO.class).insert(student);
            session.commit();
            return result;
        }
    }

    @Override
    public int update(Student student) {
        try(SqlSession session = SqlSessionFactoryProvider.getSqlSessionFactory().openSession()){
            int result = session.getMapper(StudentDAO.class).update(student);
            session.commit();
            return result;
        }
    }

    @Override
    public int delete(long id) {
        try(SqlSession session = SqlSessionFactoryProvider.getSqlSessionFactory().openSession()){
            int result = session.getMapper(StudentDAO.class).delete(id);
            session.commit();
            return result;
        }
    }
}
