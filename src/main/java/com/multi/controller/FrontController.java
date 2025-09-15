package com.multi.controller;

import com.google.protobuf.ServiceException;
import com.multi.util.LoggerUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontController", urlPatterns = { "/front/*" })
public class FrontController extends HttpServlet {
    private Map<String , SubController> controllerMap = new HashMap<>();


    @Override
    public void init() throws ServletException {
        SubController studentController = new StudentController();
//        SubController courseController = new CourseController();
//        SubController enrollmentController = new EnrollmentController();
        controllerMap.put("/students", studentController);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String path = uri.substring(contextPath.length() + "/front".length());

        String [] segments = path.split("/");
        String basePath = segments.length > 1 ? "/" + segments[1] : path;

        SubController controller = controllerMap.get(basePath);
        if(controller == null){
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        LoggerUtil.info("uri : " + uri + ", path : " + path + ", controller : " + controller.getClass().getName());
        try {
            controller.handle(req,resp,path);
        } catch (ServletException e) {
            LoggerUtil.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
