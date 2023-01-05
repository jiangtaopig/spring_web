package com.example.demo.test_sqlite;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration // 表明这是一个配置类
@ComponentScan(basePackages = {"com.example.demo.test_sqlite"}) // 指定基础包名
public class SqlConfig {
}
