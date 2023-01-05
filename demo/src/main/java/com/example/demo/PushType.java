package com.example.demo;

public enum PushType {
    /**
     * 到店提醒
     */
    MSG_STORE_REMIND("到店提醒"),

    /**
     * 送返修任务
     */
    MSG_PUSH_TASK("送返修任务"),
    /**
     * 未推修任务
     */
    MSG_NO_PUSH_TASK("未推修任务");

    private String value;

    PushType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
