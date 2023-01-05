package com.example.demo.pattern_design.abstract_factory;

public class BMWSportCar implements ISportCar{
    @Override
    public void show() {
        System.out.println("我是宝马运动汽车 M4");
    }

    @Override
    public boolean isPowerFull() {
        return true;
    }
}
