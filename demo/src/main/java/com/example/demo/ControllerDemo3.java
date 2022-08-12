package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 浏览器访问路径
 * http://localhost:8180/demo_war_exploded/controller3/demo3
 */
@Controller
@RequestMapping("/controller3")
public class ControllerDemo3 {
    @RequestMapping("/demo3")
    public String test1(Model model) {
        model.addAttribute("demo3", "demo3 是通过注解实现的,且 controller设置路径了");
        return "demo3";
    }
}

