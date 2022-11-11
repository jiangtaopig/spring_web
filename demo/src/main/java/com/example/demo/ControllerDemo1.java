package com.example.demo;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.demo.filter.OnlineNumberListener.ONLINE_NUMBER;

public class ControllerDemo1 implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("demo1_attr1","demo1:Controller会返回一个modelAndView");
        modelAndView.addObject("demo1_attr2", "demo1 的数据2");
        modelAndView.setViewName("demo1"); // 这个 viewName demo1 要和 demo1.jsp的名字一样

        // 获取 OnlineNumberListener 中设置的在线人数数据
        ServletContext context = httpServletRequest.getServletContext();
        int onlineNum = (int) context.getAttribute(ONLINE_NUMBER);
        modelAndView.addObject("demo1_attr3", "在线人数 : " + onlineNum);

        return modelAndView;
    }
}

