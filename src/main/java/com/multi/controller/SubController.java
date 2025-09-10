package com.multi.controller;

import com.google.protobuf.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface SubController {
    void handle(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException;
}
