package com.example.demo.multi_thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestSingleProducerAndConsumer {
    private List<AppleBucket> queue = new ArrayList<>(1);
    private ReentrantLock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    private final static int MAX_SIZE = 3;
    private List<AppleBucket> queue2 = new ArrayList<>(MAX_SIZE);


    public void addToBucket(AppleBucket appleBucket) {
        try {
            lock.lockInterruptibly();
            while (queue.size() == 1) {
                notEmpty.await();
            }
            queue.add(appleBucket);
            System.out.println(Thread.currentThread().getName() + "----addToBucket-----" + appleBucket);
            notFull.signal();
        } catch (InterruptedException e) {

        } finally {
            lock.unlock();
        }
    }

    public void getFromBucket() {
        try {
            lock.lockInterruptibly();
            while (queue.size() == 0) {
                notFull.await();
            }
            AppleBucket appleBucket = queue.remove(0);
            System.out.println(Thread.currentThread().getName() + "----getFromBucket-----" + appleBucket);
            notEmpty.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    /**
     * 单生产消费者模型
     */
    @Test
    public void testSingle() {
        TestSingleProducerAndConsumer singleProducerAndConsumer = new TestSingleProducerAndConsumer();
        int times = 10;

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < times; i++) {
                singleProducerAndConsumer.getFromBucket();
            }
        }, "consumer-thread");


        Thread producer = new Thread(() -> {
            for (int i = 0; i < times; i++) {
                AppleBucket appleBucket = new AppleBucket();
                appleBucket.setName("apple-" + i);
                singleProducerAndConsumer.addToBucket(appleBucket);
            }
        }, "producer-thread");

        consumer.start();
        producer.start();
    }


    /// ---------------------------- 多生产消费者模型 ---------------------------------

    private void add2BucketByMultiThread(AppleBucket appleBucket) {
        try {
            lock.lockInterruptibly();
            while (queue2.size() == MAX_SIZE) {
                System.out.println("add2BucketByMultiThread  --- await");
                notEmpty.await();
            }
            queue2.add(appleBucket);
            System.out.println(Thread.currentThread().getName() + "----add2BucketByMultiThread-----" + appleBucket);
            notFull.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void getFromBucketByMultiThread() {
        try {
            lock.lockInterruptibly();
            while (queue2.size() == 0) {
                System.out.println("--------- getFromBucketByMultiThread await-------- ");
                notFull.await();
            }
            AppleBucket appleBucket = queue2.remove(0);
            System.out.println(Thread.currentThread().getName() + "----getFromBucketByMultiThread-----" + appleBucket);
            notEmpty.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Test
    public void testMultiProducerAndConsumer() {
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 4, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>(20));
        TestSingleProducerAndConsumer singleProducerAndConsumer = new TestSingleProducerAndConsumer();


        for (int i = 0; i < 5; i++) {
            int finalI = i;
//            executor.execute(() ->{
//                AppleBucket appleBucket = new AppleBucket();
//                appleBucket.setName("apple-" + finalI);
//                singleProducerAndConsumer.add2BucketByMultiThread(appleBucket);
//            });

            new Thread(() ->{
                System.out.println("xxxxxx");
                AppleBucket appleBucket = new AppleBucket();
                appleBucket.setName("apple-" + finalI);
                singleProducerAndConsumer.add2BucketByMultiThread(appleBucket);
            }).start();
        }

        for (int i = 0; i < 5; i++) {
//            executor.execute(() ->{
//                singleProducerAndConsumer.getFromBucketByMultiThread();
//            });
            System.out.println(">>>>>>");
            new Thread(() ->{
                singleProducerAndConsumer.getFromBucketByMultiThread();
            }).start();
        }



    }

}
