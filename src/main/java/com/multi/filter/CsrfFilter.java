package com.multi.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CsrfFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException{}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        if("POST".equalsIgnoreCase(req.getMethod())){
            String csrfToken = req.getParameter("_csrf");
            String sessionToken = (String) session.getAttribute("csrfToken");

            if(sessionToken == null || !sessionToken.equals(csrfToken)){
                throw new ServletException("CSRF validation failed.");
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy(){

    }
}
