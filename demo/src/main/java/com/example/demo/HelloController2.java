package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/***
 * 不走 xml 配置，使用注解
 */
@Controller
public class HelloController2 {

    /**
     * @param model 模型
     * @return 被视图解析器处理：访问"/WEB-INF/jsp/hello3.jsp资源
     * 访问的url:RequestMapping("/hello")
     */
    @RequestMapping("/hello3")
    public String hello(Model model) {
        System.out.println(".............. hello ...................");
        //封装数据
        model.addAttribute("msg", "Hello SpringMvc_annotation");
        //被视图解析器处理：访问"/WEB-INF/jsp/hello3.jsp资源
        return "hello3";
    }
}
