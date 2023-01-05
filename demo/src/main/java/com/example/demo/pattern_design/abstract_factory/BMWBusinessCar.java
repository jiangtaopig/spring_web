package com.example.demo.pattern_design.abstract_factory;

public class BMWBusinessCar implements IBusinessCar{
    @Override
    public boolean isAutoDoor() {
        return false;
    }

    @Override
    public void show() {
        System.out.println("我是宝马商务车, 但是不是自动门");
    }
}
