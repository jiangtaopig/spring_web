package com.example.demo.entity;

import java.io.Serializable;

public class TestCompletableReq implements Serializable {

    private static final long serialVersionUID = -1506570644023865551L;

    private long businessNo;

    public long getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(long businessNo) {
        this.businessNo = businessNo;
    }
}
