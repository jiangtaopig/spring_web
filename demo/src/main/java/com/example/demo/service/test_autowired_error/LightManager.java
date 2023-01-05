package com.example.demo.service.test_autowired_error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LightManager {


//    @Autowired
    LightService lightService;

    public LightManager(LightService lightService) {
        // 因为 lightService 是通过 @Autowired 注入的，但是，构造函数的执行是在 lightService 注入之前的，所以报空指针异常
        // 修改方法就是 注释掉  @Autowired，然后把 lightService 作为构造函数的入参
        this.lightService = lightService;
        this.lightService.check();
    }



    public void start() {
        lightService.start();
    }
}
