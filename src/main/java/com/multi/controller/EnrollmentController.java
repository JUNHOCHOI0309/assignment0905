package com.multi.controller;

import com.multi.dto.Student;
import com.multi.exception.DuplicateEnrollmentException;
import com.multi.service.EnrollmentService;
import com.multi.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/enrollments")
public class EnrollmentController extends HttpServlet {
    private EnrollmentService enrollmentService;
    private StudentService studentService;

    @Override
    public void init() {
        this.enrollmentService = (EnrollmentService) getServletContext().getAttribute("enrollmentService");
        this.studentService = (StudentService) getServletContext().getAttribute("studentService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        Long studentId = Long.valueOf(req.getParameter("studentId"));

        if ("detail".equals(action)) {
            // 학생 정보와 수강 목록 가져오기
            Student student = studentService.findById(studentId);
            req.setAttribute("student", student);

            // detail.jsp로 포워드
            req.getRequestDispatcher("/WEB-INF/views/student/detail.jsp")
                    .forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        Long studentId = Long.valueOf(req.getParameter("studentId"));
        Long courseId = Long.valueOf(req.getParameter("courseId"));

        try {
            if ("cancel".equals(action)) {
                enrollmentService.cancel(studentId, courseId);
            } else if ("enroll".equals(action)) {
                enrollmentService.enroll(studentId, courseId);
            }

            // 수강 후 학생 상세 페이지로 이동
            Student student = studentService.findById(studentId);
            req.setAttribute("student", student);
            req.getRequestDispatcher("/WEB-INF/views/student/detail.jsp")
                    .forward(req, resp);

        } catch (DuplicateEnrollmentException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
    }
}
