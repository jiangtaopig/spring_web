package com.example.demo.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class MyConfiguration {

    /**
     * Order 注解，值越小优先级越高，这样在收集 MyStudent 的时候，学生李四就会排在张三的前面
     */
    @Bean("student1")
    @Order(2)
    public MyStudent getStudent1() {
       return createStudent("张三", 23);
    }

    @Bean
    @Order(1)
    public MyStudent getStudent2() {
        return createStudent("李四", 29);
    }

    private MyStudent createStudent(String name, int age) {
        MyStudent student = new MyStudent();
        student.setName(name);
        student.setAge(age);
        return student;
    }
}
