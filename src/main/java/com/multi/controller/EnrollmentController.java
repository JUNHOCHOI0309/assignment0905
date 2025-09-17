package com.multi.controller;

import com.multi.dto.Enrollment;
import com.multi.service.EnrollmentService;
import com.multi.service.EnrollmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/enrollments/*")
public class EnrollmentController extends HttpServlet {
    private final EnrollmentService enrollmentService = new EnrollmentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo(); // 예: "/", "/student/1"

        try {
            if (path != null && path.matches("^/student/\\d+$")) {
                // 특정 학생의 수강 목록 조회
                Long studentId = extractId(path);
                List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudent(studentId);
                req.setAttribute("enrollments", enrollments);
                req.getRequestDispatcher("/WEB-INF/views/enrollment/list.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Unknown path: " + path);
            }
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo(); // 예: "/", "/cancel"
        String referer = req.getHeader("Referer");

        try {
            Long studentId = Long.valueOf(req.getParameter("studentId"));
            Long courseId = Long.valueOf(req.getParameter("courseId"));

            if ("/cancel".equals(path)) {
                enrollmentService.cancel(studentId, courseId);
            } else { // 기본: 신청
                enrollmentService.enroll(studentId, courseId);
            }

            if (referer != null) {
                resp.sendRedirect(referer);
            } else {
                resp.sendRedirect(req.getContextPath() + "/enrollments/student/" + studentId);
            }
        } catch (NumberFormatException e) {
            req.setAttribute("error", "잘못된 파라미터 형식입니다.");
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
    }

    private Long extractId(String path) {
        String[] segments = path.split("/");
        for (String seg : segments) {
            if (seg.matches("\\d+")) {
                return Long.valueOf(seg);
            }
        }
        throw new IllegalArgumentException("No numeric id in path: " + path);
    }
}
