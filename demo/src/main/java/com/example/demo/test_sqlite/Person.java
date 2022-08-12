package com.example.demo.test_sqlite;

import lombok.Data;

@Data
public class Person {
    private int id;
    private String name;
    private int age;

    public Person(String name , int age) {
        this.age = age;
        this.name = name;
    }

    public Person() {

    }
}
