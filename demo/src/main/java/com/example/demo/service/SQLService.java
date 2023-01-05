package com.example.demo.service;

import org.springframework.stereotype.Repository;

@Repository("sqlService")
public class SQLService implements IDataService{
    @Override
    public void showMsg(String msg) {
        System.out.println("SQL >>>> " +msg);
    }
}
