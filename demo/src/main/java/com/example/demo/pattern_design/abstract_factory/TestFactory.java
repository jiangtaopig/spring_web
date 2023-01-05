package com.example.demo.pattern_design.abstract_factory;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestFactory {



    @Test
    public void testAbstractFactory() {

        BMWCarFactory bmwCarFactory = new BMWCarFactory();
        ISportCar bmwSportCar = bmwCarFactory.produceSportCar();
        bmwSportCar.show();
    }
}
