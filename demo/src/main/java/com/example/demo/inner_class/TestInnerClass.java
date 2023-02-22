package com.example.demo.inner_class;

public class TestInnerClass {

    public static void main(String[] args) {
        TestNoNameInnerClass testNoNameInnerClass = new TestNoNameInnerClass();
        String data = "sss";
        testNoNameInnerClass.setInterface(new TestInnerInterface() {
            @Override
            public void testSth(String name) {
                System.out.println("-------- name ------" + name+", data = " + data);
            }
        });
        testNoNameInnerClass.doSth("你试试");
    }
}
