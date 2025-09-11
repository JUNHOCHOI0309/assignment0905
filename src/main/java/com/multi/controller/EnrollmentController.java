package com.multi.controller;

import com.multi.dto.Student;
import com.multi.exception.DuplicateEnrollmentException;
import com.multi.service.EnrollmentService;
import com.multi.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        String studentIdStr = req.getParameter("studentId"); // 먼저 문자열로 받습니다.

        // --- 안전장치 1: 파라미터가 null이거나 비어있는지 확인 ---
        if (studentIdStr == null || studentIdStr.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "studentId 파라미터가 필요합니다.");
            return; // 메서드 실행을 중단합니다.
        }

        int studentIdInt;
        try {
            // --- 안전장치 2: 숫자로 변환 시도 ---
            studentIdInt = Integer.parseInt(studentIdStr);
        } catch (NumberFormatException e) {
            // 숫자가 아닌 값(예: "abc")이 들어오면 에러 처리
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "studentId는 숫자여야 합니다.");
            return;
        }

        if ("detail".equals(action)) {
            Student student = studentService.get(studentIdInt);
            req.setAttribute("student", student);

            req.getRequestDispatcher("/WEB-INF/views/student/detail.jsp")
                    .forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "유효하지 않은 action입니다.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        String studentIdStr = req.getParameter("studentId");
        String courseIdStr = req.getParameter("courseId");

        // --- 안전장치 1: 필수 파라미터들이 비어있는지 확인 ---
        if (studentIdStr == null || studentIdStr.trim().isEmpty() ||
                courseIdStr == null || courseIdStr.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "studentId와 courseId는 필수 항목입니다.");
            return;
        }

        int studentIdInt;
        Long studentId;
        Long courseId;
        try {
            // --- 안전장치 2: 숫자로 변환 시도 ---
            studentIdInt = Integer.parseInt(studentIdStr);
            studentId = Long.valueOf(studentIdInt);
            courseId = Long.valueOf(courseIdStr);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "studentId와 courseId는 숫자여야 합니다.");
            return;
        }

        try {
            if ("cancel".equals(action)) {
                enrollmentService.cancel(studentId, courseId);
            } else if ("enroll".equals(action)) {
                enrollmentService.enroll(studentId, courseId);
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "유효하지 않은 action입니다.");
                return;
            }

            Student student = studentService.get(studentIdInt);
            req.setAttribute("student", student);
            req.getRequestDispatcher("/WEB-INF/views/student/detail.jsp")
                    .forward(req, resp);

        } catch (DuplicateEnrollmentException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        } catch (Exception e) {
            // 그 외 예상치 못한 서버 오류 처리
            e.printStackTrace(); // 서버 로그에 에러 기록
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "요청 처리 중 오류가 발생했습니다.");
        }
    }
}