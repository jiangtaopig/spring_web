package com.example.demo.thread_pool;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {


    @Test
    public void test1() throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2,
                60, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        for (int i = 0; i < 4; i++) {
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " ... start ....");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " ... end ....");
            });
        }

        Thread.sleep(10_000);
        System.out.println("--- 主线程结束 ---");
    }


    @Test
    public void test2() throws InterruptedException {
        com.example.demo.thread_pool.ScheduledThreadPoolExecutor e = new  com.example.demo.thread_pool.ScheduledThreadPoolExecutor(1);
        // ScheduledThreadPoolExecutor 内置的阻塞队列中 通过 poll(keepAliveTime, TimeUnit.NANOSECONDS) 获取队列中的任务会返回 null，直到延时时间到了。
//        e.schedule(() -> {
//            System.out.println("业务逻辑");
//        }, 2, TimeUnit.SECONDS);
//
//        e.shutdown();

        long start = System.currentTimeMillis();

        e.scheduleWithFixedDelay(() -> {
            System.out.println("---------- 执行------------ 耗时 >>> " + (System.currentTimeMillis() - start));
        }, 0, 5, TimeUnit.SECONDS);

        Thread.sleep(6_000);
    }
}
