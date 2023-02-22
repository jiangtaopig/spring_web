package com.example.demo.test_exception;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestException {



    public void testException() {
//        Executors.newScheduledThreadPool(0);
        try {
            int b = 10 /0;
        } catch (Exception e) {
            System.out.println("----------------------exception 1 ----------");
            e.printStackTrace();
//            System.out.println("ex -====> " + e.printStackTrace(););
        }
    }
    @Test
    public void test1() {
//        LinkedBlockingDeque<String> blockingDeque = new LinkedBlockingDeque<>(5);
//        try {
//            blockingDeque.poll(1, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            System.out.println("e ==== > " + e.getMessage());
//            System.out.println("---------------------------------------");
//            System.out.println("ee = > " +e.toString());
//            System.out.println("===========================================");
//            e.printStackTrace();
//        }

        ScheduledExecutorService e = Executors.newScheduledThreadPool(0);
        e.schedule(() -> {
            System.out.println("业务逻辑");
        }, 60, TimeUnit.SECONDS);
        e.shutdown();
    }

    @Test
    public void test2() {
        try {
            testException();
        } catch (Exception e) {
            System.out.println("哈哈....... 程序异常啦");
        }
    }
}

