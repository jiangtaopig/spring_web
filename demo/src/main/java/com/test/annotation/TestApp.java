package com.test.annotation;

public class TestApp {
    public static void main(String[] args) throws Exception {
        ExtClassPathXmlApplicationContext app = new ExtClassPathXmlApplicationContext("com.test.annotation");
        UserService userService = (UserService) app.getBean("userService");
        userService.add("test");
    }
}
