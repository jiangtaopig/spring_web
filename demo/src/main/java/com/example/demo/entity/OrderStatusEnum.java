package com.example.demo.entity;



import java.util.Arrays;

public enum OrderStatusEnum {
    CREATE(1, "下单"),
    PAY(2, "支付"),
    DONE(3, "完成"),
    CANCEL(4, "撤销");

    private int code;
    private String message;

    OrderStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static OrderStatusEnum getOrderStatusEnum(int code) {
        return Arrays.stream(OrderStatusEnum.values()).filter(x -> x.code == code).findFirst().orElse(null);
    }

    public static void main(String[] args) {
        System.out.println("msg = " + OrderStatusEnum.CREATE.message);

        OrderStatusEnum orderStatusEnum = OrderStatusEnum.getOrderStatusEnum(2);
        System.out.println("2 -> " + orderStatusEnum.message);

        OrderStatusEnum [] enums = OrderStatusEnum.values();
        for (OrderStatusEnum statusEnum : enums) {
            System.out.println("enum code : " + statusEnum.code + " , msg = " + statusEnum.message);
        }
    }
}
