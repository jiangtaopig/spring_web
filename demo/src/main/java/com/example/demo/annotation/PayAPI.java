package com.example.demo.annotation;

import lombok.Data;

import java.math.BigDecimal;

@BankApi(url = "/bank/pay",desc = "支付")
@Data
public class PayAPI extends AbstractAPI{
    @BankApiField(order = 1, type = "N", length = 20)
    private long orderId;

    @BankApiField(order = 1, type = "N", length = 6)
    private BigDecimal amount; // 支付金额
}
