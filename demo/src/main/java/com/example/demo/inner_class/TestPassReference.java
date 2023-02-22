package com.example.demo.inner_class;

import lombok.Data;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试 java 引用传递
 */
public class TestPassReference {

    @Test
    public void test() {
        MyInfo myInfo = new MyInfo();
        myInfo.name = "aaa";
        myInfo.age = 23;

        changeMyInfo(myInfo);

        System.out.println("==== myInfo = " + myInfo);

        System.out.println("--------------------------");
        MyInfo myInfo1 = changeMyInfo2(myInfo);
        System.out.println("myInfo1 = " + myInfo1 + " , myInfo = " + myInfo);
    }

    private MyInfo changeMyInfo(MyInfo myInfo) {
        System.out.println("before change : myInfo = " + myInfo);
        myInfo.name = "change name";
        System.out.println("after change : myInfo = " + myInfo);
        return myInfo;
    }

    /**
     * 开始的时候 myInfo2 和 myInfo 都指向通一个对象，
     * 后面 myInfo2 指向了新建的对象 myInfo1，但是实参 myInfo 指向的还是原来的对象
     */
    private MyInfo changeMyInfo2(MyInfo myInfo2) {
        MyInfo myInfo1 = new MyInfo();
        myInfo1.name = "啧啧啧";
        myInfo1.age = 12;
        myInfo2 = myInfo1;
        return myInfo2;
    }

    @Data
    static class MyInfo {
        private String name;
        private int age;
    }


    @Test
    public void test2() throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        CountDownLatch latch = new CountDownLatch(2);
        long start = System.currentTimeMillis();
        Runnable runnable = () -> {
            for (int i = 0; i < 1000000000; i++) { // int 表示是一个可数循环
                atomicInteger.addAndGet(1);
                if (i % 1000 == 0) {
                    try {
                        Thread.sleep(0); // 注释【1】 调用 sleep 方法的线程会进入 SafePoint
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(Thread.currentThread().getName() + " >> 执行结束");
            latch.countDown();
        };

        new Thread(runnable, "thread_A").start();
        new Thread(runnable, "thread_B").start();

        // 1.启动了两个长的、不间断的循环（内部没有安全点检查）------> 注释【1】是解决问题加的安全点检查
        // 2.主线程进入睡眠状态 1 秒钟。
        // 3.在1000 ms之后，JVM尝试在 SafePoint 停止，以便Java线程进行定期清理，但是直到可数循环完成后才能执行此操作。
        // 4.主线程的 Thread.sleep 方法从 native 返回，发现安全点操作正在进行中，于是把自己挂起，直到操作结束。
        // 解决方法就是加入注释【1】
        Thread.sleep(1000);

        System.out.println("cost = " + (System.currentTimeMillis() - start));
        System.out.println("num = " + atomicInteger.get());

        latch.await();
        System.out.println("2个子线程任务执行完成，到这里耗时 " + (System.currentTimeMillis() - start));
    }
}
