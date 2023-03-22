package com.example.demo.beanProcessor;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:demo_scan.xml")
public class Test {

    @Autowired
    BaseService baseService;

    @org.junit.Test
    public void test1() {
        baseService.eat();
    }

}
