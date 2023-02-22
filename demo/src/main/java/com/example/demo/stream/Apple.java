package com.example.demo.stream;

import lombok.Data;



public  class Apple {
    private String name;
    private int weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
