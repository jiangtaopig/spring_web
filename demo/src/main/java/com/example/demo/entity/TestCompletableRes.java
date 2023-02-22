package com.example.demo.entity;

import java.io.Serializable;

public class TestCompletableRes implements Serializable {

    private static final long serialVersionUID = -4513762858714777911L;

    private String costTime;
    private String result;

    public String getCostTime() {
        return costTime;
    }

    public void setCostTime(String costTime) {
        this.costTime = costTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
