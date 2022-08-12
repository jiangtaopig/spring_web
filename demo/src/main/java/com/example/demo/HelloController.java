package com.example.demo;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        System.out.println("----------------- handleRequest -------------------");
        //1 创建modelAndView
        ModelAndView mv = new ModelAndView();
        //2 调用业务层，这里没有，就不写
        //3 封装对象，放在mv中添加
        mv.addObject("msg", "Hello SpringMvc little pig");
        //4 封装要跳转的视图，WEB-INF/jsp/hello3.jsp
        mv.setViewName("hello3");
        // http://localhost:8180/demo_war_exploded/hello4 调用页面，
        // hello4 是定义在 spring_mvc_servlet.xml 中，用来获取 HelloController 对象的
        return mv;
    }
}

