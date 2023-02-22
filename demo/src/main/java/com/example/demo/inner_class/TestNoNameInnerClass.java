package com.example.demo.inner_class;

public class TestNoNameInnerClass {

    private TestInnerInterface testInnerInterface;

    public void setInterface(TestInnerInterface testInnerInterface) {
        this.testInnerInterface = testInnerInterface;
    }

    public void doSth(String name) {
        testInnerInterface.testSth(name);
    }
}
