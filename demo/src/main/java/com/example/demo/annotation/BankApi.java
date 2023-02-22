package com.example.demo.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Documented
public @interface BankApi {
    // 包含 url 和 接口说明
    String desc() default "";
    String url() default "";
}
