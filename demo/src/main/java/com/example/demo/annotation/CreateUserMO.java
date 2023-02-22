package com.example.demo.annotation;

import lombok.Data;

@Data
public class CreateUserMO {
    private String name; // 姓名参数固定为10位，不足的后面补下划线_,即字符串内容靠左， 拼接顺序 排 1
    private String idCard; // 固定为18位，不足的后面补下划线_,即字符串内容靠左， 拼接顺序 排 2
    private String mobile; // 固定为11位，不足的后面补下划线_,即字符串内容靠左， 拼接顺序 排 4
    private int age; // 固定为5位，不足的前面补0,即数字靠右， 拼接顺序 排 3

    /**
     * 目标是实现如下功能，但是这样太不优雅了，
     * 1. 比如姓名、手机号和身份证号又重复的逻辑
     * 2. 假设要拼接的属性很多，那么就很容易出错
     * 现在使用注解来实现：
     */

    private void createUser() {
        StringBuilder stringBuilder = new StringBuilder();
        //字符串靠左，多余的地方填充_
        stringBuilder.append(String.format("%-10s", name).replace(' ', '_')); // 姓名排1
        //字符串靠左，多余的地方填充_
        stringBuilder.append(String.format("%-18s", idCard).replace(' ', '_')); // 身份证排2
        //数字靠右，多余的地方用0填充
        stringBuilder.append(String.format("%05d", age)); // 年龄排3
        //字符串靠左，多余的地方用_填充
        stringBuilder.append(String.format("%-11s", mobile).replace(' ', '_')); //

        // 拼接完成后的样式如下：
        String userStr = "zhupig____"+"340823199011116666"+"00023"+"13612345678";
    }
}
