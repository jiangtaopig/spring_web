package com.example.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyFilter implements Filter {

    private MyHandler myHandler;

    protected static Logger logger = LoggerFactory.getLogger(MyFilter.class);

    private AtomicBoolean isFiltered = new AtomicBoolean(false);

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("MyFilter >>> init ----------");
        myHandler = SpringBeanLocator.getBean(MyHandler.class);
        System.out.println("MyFilter myHandler = " + myHandler);
        logger.info("----------- logger init ---------------");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("MyFilter >>> doFilter --------" + this+", isFiltered =  " + isFiltered.get());
        if (isFiltered.get()) {
            logger.info("----- MyFilter already doFilter ------");
            return; // return 之后后面的接口就无法请求了，
        }
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        //获取 API
        String api = request.getRequestURI();
        logger.info(">>>>> api: {}", api);
        filterChain.doFilter(request, response);

//        if ("/demo_war_exploded/getUserInfo".equals(api))
//            isFiltered.set(true);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
