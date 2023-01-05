package com.example.demo.service.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 由于使用了 AspectJ ,需要在 demo_sacn.xml 中加上 ：
 * <aop:aspectj-autoproxy proxy-target-class="true" expose-proxy="true"/>
 * 否则报：Cannot find current proxy: Set 'exposeProxy' property on Advised to 'true' to make it available
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:demo_sacn.xml")
@PropertySource({"classpath:application.properties"})
public class AopHandler {

    @Autowired
    ElectricService electricService;

    @Test
    public void test() {
        try {
            electricService.charge();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        try {
            // forName jvm 加载这个类，并会初始化静态属性和执行静态代码块
            Class<?> clazz = Class.forName("com.example.demo.service.aop.AdminUserService");
            // newInstance 真正实例化这个类，以及类里面的非静态属性
            AdminUserService adminUserService = (AdminUserService) clazz.newInstance();
            adminUserService.login();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
