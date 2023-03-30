package com.example.demo.service.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopConfig {

    @Around(value = "execution(* com.example.demo.service.aop.ElectricService.pay(..))")
    public void recordPayCostTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        joinPoint.proceed();
        long end = System.currentTimeMillis();
        System.out.println("Pay method time cost（ms）: " + (end - start));
    }

    @Before(value = "execution(* com.example.demo.service.aop.AdminUserService.login(..))")
    public void logAdminLogin(JoinPoint joinPoint) {
        System.out.println("----------- admin login --------------");
    }

    // 匹配 包名是 com.example.demo.service.aop 开头的，且被 CheckRequiredParam 注解的方法
    @Pointcut("execution(* com.example.demo.service.aop.*.*(..)) &&@annotation(com.example.demo.service.aop.CheckRequiredParam)")
    public void doCheck() {
    }

    @Around("doCheck()")
    public void checkParam(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("--------- enter checkParam -------");
        proceedingJoinPoint.proceed();
        System.out.println("-------- after checkParam ----------");
    }

}
