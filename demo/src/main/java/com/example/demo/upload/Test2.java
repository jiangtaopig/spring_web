package com.example.demo.upload;

import com.example.demo.MySingleton;

public class Test2 {
    public static void main(String[] args) {
        MySingleton.getSingleton();
        System.out.println("===============================");
        MySingleton.getSingleton();

        ThreadLocal<String> t1 = new ThreadLocal<>();
        t1.set("111");

        t1.get();
    }
}
