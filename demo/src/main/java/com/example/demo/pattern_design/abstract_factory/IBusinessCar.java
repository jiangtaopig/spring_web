package com.example.demo.pattern_design.abstract_factory;

/**
 * 商务车
 */
public interface IBusinessCar extends ICar{
    /**
     * 是否是自动门，商务车大部分是自动门
     */
    boolean isAutoDoor();
}
