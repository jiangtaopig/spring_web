package com.example.demo.inner_class;

public class InnerOutClass {
    private String name;

    class InnerClass {

        private int age;

        public void showOutClassName() {
            System.out.println("name = " + name);
        }
    }
}
