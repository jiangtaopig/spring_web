package com.example.dubboconsumer;

import com.example.demoapi.TestInterface;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class MainApp {


    @SuppressWarnings("resource")
    public static void main(String[] args) throws IOException {
        System.out.println("dubbo costomer start!");

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        context.start();

        TestInterface demoService = (TestInterface) context.getBean("demoService"); // 获取远程服务代理
        System.out.println("demoService = " + demoService);
        String  ss = demoService.doTest("啧啧啧"); // 执行远程方法
        System.out.println("---------------------");
		System.out.println(ss); // 显示调用结果

        // 只有开着消费者，才能在控制台看到。为保证服务一直开着，利用输入流的阻塞来模拟
        System.in.read();
    }

}
