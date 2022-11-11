package com.example.dubboprovider;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.demoapi.TestInterface;

@Service(version = "1.0.0")
public class TestMyImpl implements TestInterface {
    @Override
    public String doTest(String s) {
        System.out.println("------------ dubboprovider ---------- TestMyImpl >>> " + s);
        return "------------ dubboprovider ---------- TestMyImpl >>> " + s;
    }
}
