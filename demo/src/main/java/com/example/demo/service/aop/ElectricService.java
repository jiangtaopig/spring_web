package com.example.demo.service.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 模拟充电付费逻辑
 */
@Service
public class ElectricService {

    @Autowired
    ElectricService electricService;

    @Autowired
    AdminUserService adminUserService;

    public void charge() throws Exception {
        System.out.println("Electric charging ...");
//        this.pay(); // 直接使用 this 引用到的只是一个普通对象，无法实现AOP的功能

        // 修改方法1 ： 从 AopContext 中获取
//        ElectricService electricService = (ElectricService) AopContext.currentProxy();
//        electricService.pay();
        // 修改方法 2 ：使用 @Autowired 注解引入 electricService
        electricService.pay();
    }

    /**
     * 假设这个 付费是支付宝支付，由于是第3方的服务，
     * 我们需要记录下接口调用时间。这时候我们就引入了一个 @Around 的增强 ，
     * 分别记录在 pay() 方法执行前后的时间，并计算出执行 pay() 方法的耗时。
     *
     * @throws Exception
     */
    public void pay() throws Exception {
        adminUserService.login();
        // 由于 通过动态代理获取的 adminUserService 对象，不会初始化属性 user，所以会报 NPE
//        String payNum = adminUserService.user.getPayNum();
        // 改成这个方法，这个方法最终会调用 DynamicAdvisedInterceptor 的 intercept 方法去获取被代理的原生对象，
        // 而原始对象中类的属性是被实例化过的且存在的，因此可行
        String payNum = adminUserService.getUser().getPayNum();
        System.out.println("user payNum = " + payNum);
        System.out.println("Pay with alipay ...");
        Thread.sleep(1000);
    }
}
