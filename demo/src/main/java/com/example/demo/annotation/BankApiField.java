package com.example.demo.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
@Inherited
@Documented
public @interface BankApiField {
    // 包含参数的次序、类型和限制长度
    int order() default -1;
    int length() default -1;
    String type() default ""; // S 表示字符串类型， N 表示数值类型
}
