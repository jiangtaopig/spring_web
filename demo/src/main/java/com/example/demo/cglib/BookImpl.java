package com.example.demo.cglib;

public class BookImpl implements IBook{
    @Override
    public String read(String bookName) {
        return bookName + ", 非常棒的一本书！！！";
    }
}
