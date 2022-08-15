package com.example.demo;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

// 注释掉如下的 @WebServlet 注解，也可以去 web.xml 中去注册 HelloServlet
@WebServlet(name = "helloServlet", value = "/zjt-hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!111";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        System.out.println("---------------doGet---------------");
//        response.setContentType("text/html");
//
//        // Hello
//        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
//        out.println("<h1>" + message + "</h1>");
//        out.println("</body></html>");

        //1 获得参数
        //2 调用业务层
        //3 视图转发或者重定向
        request.getRequestDispatcher("/WEB-INF/jsp/download.jsp").forward(request, response);
    }

    public void destroy() {
    }
}