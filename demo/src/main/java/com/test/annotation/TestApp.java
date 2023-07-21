package com.test.annotation;


import java.util.Timer;
import java.util.TimerTask;

public class TestApp {
    public static void main(String[] args) throws Exception {
        ExtClassPathXmlApplicationContext app = new ExtClassPathXmlApplicationContext("com.test.annotation");
        UserService userService = (UserService) app.getBean("userService");
        userService.add("test");


//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("111111111111");
//            }
//        }, 2000);
//
//        Thread.sleep(10000);
    }
}
