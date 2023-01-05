package com.example.demo.pattern_design.abstract_factory;

public class BenzSportCar implements ISportCar {

    @Override
    public void show() {
        System.out.println("我是奔驰AMG");
    }

    @Override
    public boolean isPowerFull() {
        return true;
    }
}
