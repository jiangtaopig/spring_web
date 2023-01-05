package com.example.demo.pattern_design.abstract_factory;

public class BMWCarFactory implements ICarFactory {
    @Override
    public ISportCar produceSportCar() {
        return new BMWSportCar();
    }

    @Override
    public IBusinessCar produceBusinessCar() {
        return new BMWBusinessCar();
    }
}
