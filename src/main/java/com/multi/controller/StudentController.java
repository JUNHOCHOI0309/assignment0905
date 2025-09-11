package com.multi.controller;

import com.multi.dto.PageRequest;
import com.multi.dto.PageResult;
import com.multi.dto.Student;
import com.multi.service.StudentService;
import com.multi.service.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StudentController implements SubController{
    private final StudentService studentService = new StudentServiceImpl();
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        String method = request.getMethod();

        switch (method) {
            case "GET":
                handleGet(request, response, path);
                break;
            case "POST":
                handlePOST(request, response, path);
                break;
            default:
                response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
    }

    private void handleGet(HttpServletRequest request, HttpServletResponse response, String path) throws IOException, ServletException {
        switch (path) {
            case "/students":
                list(request, response);
                return;
            case "/students/new":
                newForm(request,response);
                return;
            default:
                if(path.matches("^/students/\\d+$")){
                    detail(request,response);
                    return;
                }
                if(path.matches("^/students/\\d+/edit$")){
                    editForm(request,response);
                    return;
                }
                response.sendError(HttpServletResponse.SC_NOT_FOUND,"Unknown path:" + path);
        }
    }

    private void handlePOST(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
        switch (path) {
            case "/students":
                create(request,response);
                return;
            default:
                if(path.matches("^/students/\\d+/update$")){
                    update(request,response);
                    return;
                }
                if(path.matches("^/students/\\d+/delete$")){
                    delete(request,response);
                    return;
                }
                response.sendError(HttpServletResponse.SC_NOT_FOUND,"Unknown path:" + path);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String q = request.getParameter("q");
        int page = parseInt(request.getParameter("page"),1);
        int size = parseInt(request.getParameter("size"),10);

        PageResult<Student> result = studentService.list(q, new PageRequest(page,size));
        request.setAttribute("page",result);
        request.setAttribute("q",q);
        request.getRequestDispatcher("/WEB-INF/views/common/list.jsp").forward(request,response);
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = extractId(request.getPathInfo());
        Student s = studentService.get(id);
        request.setAttribute("student",s);
        request.getRequestDispatcher("/WEB-INF/views/student/detail.jsp").forward(request,response);
    }

    private void newForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("mode","create");
        request.setAttribute("action",request.getContextPath() + "/front/students");
        request.getRequestDispatcher("/WEB-INF/views/student/form.jsp").forward(request,response);
    }

    private void editForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = extractId(request.getPathInfo());
        Student s = studentService.get(id);
        request.setAttribute("mode","edit");
        request.setAttribute("student",s);
        request.setAttribute("action",request.getContextPath() + "/front/students/" + id);
        request.getRequestDispatcher("/WEB-INF/views/student/form.jsp").forward(request,response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Student student = bind(request);
        studentService.create(student);
        response.sendRedirect(request.getContextPath() + "/front/students");
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        int id = extractId(request.getPathInfo());
        Student s = bind(request);
        s.setId(id);
        studentService.update(s);
        response.sendRedirect(request.getContextPath() + "/front/students/" + id);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("DEBUG delete pathInfo = " + request.getPathInfo());
        int id = extractId(request.getPathInfo());
        studentService.delete(id);
        response.sendRedirect(request.getContextPath() + "/front/students");
    }

    private Student bind(HttpServletRequest request){
        Student student = new Student();
        student.setStudentNo(request.getParameter("studentNo"));
        student.setName(request.getParameter("name"));
        student.setEmail(request.getParameter("email"));
        student.setDept(request.getParameter("dept"));
        return student;
    }

    private int extractId(String path){
        String[] segments = path.split("/");
        for (String seg : segments) {
            if (seg.matches("\\d+")) {
                return Integer.parseInt(seg);
            }
        }
        throw new IllegalArgumentException("No numeric id in path: " + path);
    }

    private int parseInt(String v, int def) {
        try {
            return Integer.parseInt(v);
        } catch (Exception e) {
            return def;
        }
    }
}