package com.example.demo.utils;

public class StringUtil {

    private StringUtil() {

    }

    public static boolean isNotBlank(String value) {
        return !isBlank(value);
    }

    public static boolean isBlank(String value) {
        return null == value || value.length() == 0 || "".equals(value) || "".equals(value.trim());
    }
}
