package com.example.demo;

import org.junit.Test;

public enum CustomerSourceEnum {

    AUTO_ISSUE("AUTO_ISSUE", "车险出单"),
    NON_AUTO_ISSUE("NON_AUTO_ISSUE", "非车出单"),
    SALES_REPAIR("SALES_REPAIR", "售后维修"),
    ACC_REPAIR("ACC_REPAIR", "事故推修"),
    BASE_IMPORT("BASE_IMPORT", "基盘导入"),
    MARKET_ACTIVITE("MARKET_ACTIVITE", "营销活动");

    private String code;
    private String message;

    CustomerSourceEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }


    public static void main(String[] args) {
        System.out.println("sss = " + CustomerSourceEnum.AUTO_ISSUE.getMessage()+", code = " + CustomerSourceEnum.AUTO_ISSUE.getCode());
    }

}
