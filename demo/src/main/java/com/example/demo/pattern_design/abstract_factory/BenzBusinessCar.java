package com.example.demo.pattern_design.abstract_factory;

public class BenzBusinessCar implements IBusinessCar{
    @Override
    public boolean isAutoDoor() {
        return true;
    }

    @Override
    public void show() {
        System.out.println("我是奔驰商务车，是自动门");
    }
}
