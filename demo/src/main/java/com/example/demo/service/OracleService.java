package com.example.demo.service;

import org.springframework.stereotype.Repository;

@Repository
public class OracleService implements IDataService{
    @Override
    public void showMsg(String msg) {
        System.out.println("oracle >>> " + msg);
    }
}
