package com.example.demo.pattern_design.abstract_factory;

public class BenzCarFactory implements ICarFactory{
    @Override
    public ISportCar produceSportCar() {
        return new BenzSportCar();
    }

    @Override
    public IBusinessCar produceBusinessCar() {
        return new BenzBusinessCar();
    }
}
