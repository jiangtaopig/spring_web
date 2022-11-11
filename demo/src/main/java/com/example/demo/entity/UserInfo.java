package com.example.demo.entity;

import lombok.Data;

@Data
public class UserInfo {
    private String userId;
    private String name;
    private int age;
    private Address address;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Data
    public static class Address{
        private String street;
        private int room;
    }
}
