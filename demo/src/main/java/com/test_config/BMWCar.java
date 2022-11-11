package com.test_config;

import org.springframework.stereotype.Component;

@Component
public class BMWCar implements ICar {

    private IDecorate decorate;

    public BMWCar(IDecorate decorate) {
        this.decorate = decorate;
    }

    @Override
    public void run() {
        System.out.println(" I am BMW , running fast");
        decorate.display();
    }
}
