package com.example.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@PropertySource({"classpath:application.properties"}) // 引入配置文件
@ContextConfiguration("classpath:demo_scan.xml")
public class MyTestConfig {

    @Value("${passWord}") // 读取配置文件的值
    String pwd;

    @Value("${userName}")
    String userName;

    @Test
    public void testV() {
        System.out.println("---------------- pwd = " + pwd +" , userName = " + userName);
    }
}
