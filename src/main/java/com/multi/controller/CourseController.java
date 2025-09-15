package com.multi.controller;

import com.multi.dto.Course;
import com.multi.exception.AppException;
import com.multi.exception.NotFoundException;
import com.multi.exception.ValidationException;
import com.multi.service.CourseService;
import com.multi.service.CourseServiceImpl;
import com.multi.util.LoggerUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/courses")
public class CourseController extends HttpServlet {
    private final CourseService courseService = new CourseServiceImpl();
    /**
     * GET 요청 처리
     * - action=null 또는 list : 목록 페이지 (검색/페이징 포함)
     * - action=view : 상세 페이지
     * - action=write : 등록 폼
     * - action=edit : 수정 폼
     */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if(action == null || action.equals("list")){
            String q    = req.getParameter("q");
            int page = req.getParameter("page") == null ? 1 : Integer.parseInt(req.getParameter("page"));
            int size = req.getParameter("size") == null ? 10 : Integer.parseInt(req.getParameter("size"));

            List<Course> courses = courseService.findAll(q, page, size);
            int totalCount = courseService.countAll(q);

            req.setAttribute("courses", courses);
            req.setAttribute("q", q == null ? "" : q);
            req.setAttribute("page", page);
            req.setAttribute("size", size);
            req.setAttribute("totalCount", totalCount);

            req.getRequestDispatcher("/WEB-INF/views/course/list.jsp").forward(req, resp);
        }else if (action.equals("view")) {
            Long id = Long.valueOf(req.getParameter("id"));
            Course course = courseService.findById(id);
            req.setAttribute("course", course);
            req.getRequestDispatcher("/WEB-INF/views/course/detail.jsp").forward(req, resp);

        } else if (action.equals("write")) { // 등록 폼
            req.getRequestDispatcher("/WEB-INF/views/course/write.jsp").forward(req, resp);

        } else if (action.equals("edit")) { // 수정 폼
            Long id = Long.valueOf(req.getParameter("id"));
            Course course = courseService.findById(id);
            req.setAttribute("course", course);
            req.getRequestDispatcher("/WEB-INF/views/course/edit.jsp").forward(req, resp);

        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
    /**
     * POST 요청 처리
     * - action=create : 등록 처리
     * - action=update : 수정 처리
     * - actio=delete : 삭제 처리
     * 처리 후에는 목록으로 리다이렉트
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        String id = req.getParameter("id");
        String code = req.getParameter("code");
        String title = req.getParameter("title");
        String professor = req.getParameter("professor");
        int credit = req.getParameter("credit") == null ? 3 : Integer.parseInt(req.getParameter("credit"));

        if ("create".equals(action)) { // 등록
            Course c = new Course();
            c.setCode(code);
            c.setTitle(title);
            c.setProfessor(professor);
            c.setCredit(credit);
            try{
                courseService.insert(c);
                LoggerUtil.info("학생 생성 완료 : " + c.toString());
            } catch (ValidationException e){
                LoggerUtil.warn("학성 생성 실패 : " + c.toString());
                throw e;
            } catch (Exception e){
                new AppException(e.getMessage(), e);
            }
        } else if ("update".equals(action)) { // 수정
            Course c = new Course();
            c.setId(Long.valueOf(id));
            c.setCode(code);
            c.setTitle(title);
            c.setProfessor(professor);
            c.setCredit(credit);
            try{
                courseService.update(c);
                LoggerUtil.info("학생 업데이트 완료 : " + c.toString());
            } catch (NotFoundException e){
                LoggerUtil.warn("학성 업데이트 실패 : " + c.toString());
                throw e;
            } catch (Exception e){
                new AppException(e.getMessage(), e);
            }
        } else  if ("delete".equals(action)) { // 삭제
            try{
                courseService.delete(Long.valueOf(id));
                LoggerUtil.info("학생 업데이트 완료 : " + Long.valueOf(id));
            } catch (NotFoundException e){
                LoggerUtil.warn("학성 업데이트 실패 : " + Long.valueOf(id));
                throw e;
            } catch (Exception e){
                new AppException(e.getMessage(), e);
            }
        }

        resp.sendRedirect(req.getContextPath() + "/courses?action=list");
    }
}
