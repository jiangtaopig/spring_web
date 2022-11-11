package com.example.demo.cglib;

import org.junit.Test;

public class TestCglib {


    @Test
    public void test() {
        MyDao myDao = new MyDao();
        MyDao factory = (MyDao) new MyFactory(myDao).getProxyInstance();
        factory.save();
    }
}
