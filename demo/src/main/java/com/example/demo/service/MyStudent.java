package com.example.demo.service;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MyStudent {
    private String name;
    private int age;
}
