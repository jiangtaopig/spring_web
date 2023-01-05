package com.example.demo.pattern_design.abstract_factory;

/**
 * 抽象工厂
 */
public interface ICarFactory {

    /**
     * 生产运动汽车
     */
    ISportCar produceSportCar();

    /**
     * 生产商务车
     */
    IBusinessCar produceBusinessCar();
}
