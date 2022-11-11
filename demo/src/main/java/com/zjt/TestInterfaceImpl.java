package com.zjt;

import com.example.demoapi.TestInterface;
import org.springframework.stereotype.Component;

@Component
public class TestInterfaceImpl implements TestInterface {
    @Override
    public String doTest(String s) {
        System.out.println("TestInterfaceImpl xxxxxxxx--------- " + s);
        return "TestInterfaceImpl xxxxxxxx--------- " + s;
    }
}
