package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerDemo2 {
    @RequestMapping("/demo2")
    public String test1(Model model) {
        model.addAttribute("demo2", "demo2 是通过注解实现的");
        return "demo2";
    }
}

