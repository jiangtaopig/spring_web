package com.example.demo.beanProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("bean 对象初始化之前.......... beanName >> " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 为当前 bean 对象注册监控代理对象，负责增强 bean 对象方法的能力
        Class beanClass = bean.getClass();
        if (beanClass == ISomeService.class) {
            Object proxy = Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                /**
                 * @param proxy  代理监控对象
                 * @param method doSome()方法
                 * @param args   doSome()方法执行时接收的实参
                 * @return
                 * @throws Throwable
                 */
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    String methodName = method.getName();
                    System.out.println("ISomeService 中的 doSome() or eat 被拦截了···methodName = " + methodName);
                    if ("doSth".equals(methodName) || "eat".equals(methodName)) {
                        String result = (String) method.invoke(bean, args);
                        return result.toUpperCase();
                    }
                    return method.invoke(bean, args);
                }
            });
            return proxy;
        }
        return bean;
    }
}
