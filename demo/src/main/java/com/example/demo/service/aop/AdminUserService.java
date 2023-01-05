package com.example.demo.service.aop;

import org.springframework.stereotype.Service;

@Service
public class AdminUserService {
    public final User user = new User("881121");

    public void login() {
        System.out.println("----------- AdminUserService login ---------- " );
    }

    public User getUser() {
        return user;
    }
}
