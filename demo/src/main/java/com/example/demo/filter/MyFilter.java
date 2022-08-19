package com.example.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyFilter implements Filter {

    private MyHandler myHandler;
    protected static Logger logger = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("MyFilter >>> init ----------");
        myHandler = SpringBeanLocator.getBean(MyHandler.class);
        System.out.println("myHandler = " + myHandler);
        logger.info("----------- logger init ---------------");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("MyFilter >>> doFilter --------");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取 API
        String api = request.getRequestURI();
        logger.info(">>>>> api: {}", api);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
