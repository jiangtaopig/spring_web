package com.zjt;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// 这个会自动扫描 com.zjt 这个包以及他的所有子包
@Configuration // 表明这是一个配置类
@ComponentScan(basePackages = {"com.zjt"}) // 指定基础包名
public class CDPlayerConfig {
}
