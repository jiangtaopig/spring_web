package com.test.annotation;

@ZjtService(value = "userService")
public class UserServiceImpl implements UserService{

    @ZjtAutoWired
    OrderService orderService;

    @Override
    public void add(String data) {
        System.out.println("####UserServiceImpl====添加" + data);
        orderService.add();
    }
}
