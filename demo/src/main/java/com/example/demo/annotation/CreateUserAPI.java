package com.example.demo.annotation;

import lombok.Data;

@BankApi(url = "/bank/create/user", desc = "创建银行用户")
@Data
public class CreateUserAPI extends AbstractAPI {

    @BankApiField(order = 1, type = "S", length = 10)
    private String name;

    @BankApiField(order = 2, type = "S", length = 18)
    private String idCard;

    @BankApiField(order = 4,type = "S", length = 11)
    private String mobile;

    @BankApiField(order = 3, type = "N", length = 5)
    private int age;
}
