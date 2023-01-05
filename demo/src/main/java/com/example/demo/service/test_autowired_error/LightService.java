package com.example.demo.service.test_autowired_error;

import org.springframework.stereotype.Service;

@Service
public class LightService {

    public void start() {
        System.out.println("----- 开灯 -------");
    }

    public void turnOffLight() {
        System.out.println("----- 关灯 -------");
    }

    public void check() {
        System.out.println(" --------- 检查灯是否正常 ----------");
    }
}
