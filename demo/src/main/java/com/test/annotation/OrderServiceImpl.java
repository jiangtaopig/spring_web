package com.test.annotation;

@ZjtService(value = "orderService")
public class OrderServiceImpl implements OrderService{
    @Override
    public void add() {
        System.out.println("####OrderServiceImpl====依赖注入添加");
    }
}
