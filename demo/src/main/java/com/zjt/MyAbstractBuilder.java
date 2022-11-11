package com.zjt;


public abstract class MyAbstractBuilder {
    String keyWord;

    public abstract String getBusinessType();

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
