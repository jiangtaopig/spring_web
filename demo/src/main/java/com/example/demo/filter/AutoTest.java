package com.example.demo.filter;

import org.junit.Test;

public class AutoTest {

    @Test
    public void testTime() {
        // 这个不是表示 10s 而是 101s = 101000，切记不可搞错了，10s 就写成 10 * 1000
        int time = 10_1000;
        System.out.println("time >>> " + time);
    }
}
