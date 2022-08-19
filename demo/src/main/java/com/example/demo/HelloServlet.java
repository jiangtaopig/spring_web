package com.example.demo;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

// 注释掉如下的 @WebServlet 注解，也可以去 web.xml 中去注册 HelloServlet
@WebServlet(name = "helloServlet", value = "/zjt-hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!111 中国";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("---------------doGet---------------");
//        response.setContentType("text/html");
//
//        // Hello
        //设置response的编码，否则浏览器打开的时候中文会乱码
        response.setContentType("text/html;charset=UTF-8");
        // 因为 cookie 中不能包含空格，所以年月日后面加了 _ 连接时分秒
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        PrintWriter out = response.getWriter();
        Cookie[] cookies = request.getCookies();
        String cookieValue = "" ;
        for (Cookie cookie : cookies) {
            if ("time".equals(cookie.getName())) {
                out.write("您上次登陆的时间是：");
                cookieValue = cookie.getValue();
                out.write(cookieValue);
                out.write("<br/>");
                // 设置超链接跳转到 zjt-book-servlet
                out.write("<a href=\"zjt-book-servlet\">Hello Book</a>");
                out.write("<br/>");
                cookie.setValue(simpleDateFormat.format(new Date()));
                response.addCookie(cookie);
                //既然已经找到了就可以break循环了
                break;
            }
        }

        //如果Cookie的值是空的，那么就是第一次访问
        if (cookieValue.isEmpty()) {
            String date = simpleDateFormat.format(new Date());
            System.out.println("----"+date+"------");
            Cookie cookie = new Cookie("time", date);
            cookie.setMaxAge(100000);
            response.addCookie(cookie);
            out.write("您是第一次登录啊...");
        }

//        out.println("<html><body>");
//        out.println("<h1>" + message + "</h1>");
//        out.println("</body></html>");

        //1 获得参数
        //2 调用业务层
        //3 视图转发或者重定向
        // 上传的例子
//        request.getRequestDispatcher("/WEB-INF/jsp/upload_info.jsp").forward(request, response);
    }

    private void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/upload_info.jsp").forward(request, response);
    }

    public void destroy() {
    }
}