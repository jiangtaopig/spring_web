package com.example.demo.define_classloader.swap;

@MyTransactional
public class MyServiceImpl implements IMyService {

    private String target;
    private int num;

    public MyServiceImpl(String target, int num) {
        this.target = target;
        this.num = num;
    }

    @Override
    public void doSth(String params) {
        System.out.println(params + "_" + "hello" + " __ "+ target + "_num = " + num);
    }
}
