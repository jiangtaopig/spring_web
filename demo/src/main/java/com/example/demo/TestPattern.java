package com.example.demo;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestPattern {

    @Test
    public void testPatternMain() {
        boolean ff = isValidEmail("123@163.com");
        System.out.println("ff = " + ff);

//        String p = "([3-5][A-Za-z0-9])+";
//        String s = "4a5b3c";


        // * 表示0次或多次， + 表示一次或多次， [-.]就是实打实的匹配 - 和 . , [^0-9] 表示不匹配从0到9
//        String reg = "^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$";
//        String s = "3.45555@qq.com";

        // 表示不匹配从1到9的数据
//        String reg = "[^1-9]";
//        String s = "0";

        // 只匹配 '.'
        String reg = "\\.";
        String s = ".";

        boolean f = Pattern.matches(reg, s);
        System.out.println("===== f ====" + f);
    }

    public boolean isValidEmail(String email) {
        if ((email != null) && (!email.isEmpty())) {
            return Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", email);
        }
        return false;
    }


    @Test
    public void testPattern2() {
        String s = "name:#pig_zhu#, who are you #sale#";
        String reg = "#.*?#";

        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            String originKey = matcher.group();
            String replacementKey = originKey.replace("#", "");

            System.out.println("originKey = " + originKey + " , replacementKey = " + replacementKey);

        }
    }

}
