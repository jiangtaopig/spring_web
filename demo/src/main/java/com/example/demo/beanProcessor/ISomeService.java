package com.example.demo.beanProcessor;

import org.springframework.stereotype.Component;

@Component
public class ISomeService implements BaseService {
    @Override
    public String doSth() {
        return "zhujiangtao";
    }

    @Override
    public String eat() {
        return "banana";
    }
}
