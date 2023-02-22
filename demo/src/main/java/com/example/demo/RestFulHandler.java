package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class RestFulHandler {


    /**
     * 使用 CompletableFuture 来异步执行多个接口，获取接口的执行结果
     */
    public TestMy.UserInfo2 getUserInfo() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ExecutorService service = new ThreadPoolExecutor(3, 5, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5));

        // 现在想要获取 用户信息，但是用户基础信息和分数信息不在同一个接口，就可以如下去处理
        TestMy.UserInfo2 userInfo = new TestMy.UserInfo2();
        long id = 10010;

        CompletableFuture<TestMy.User2> userFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return getRemoteUserAndFill(id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }, service);

        CompletableFuture<TestMy.UserScore> scoreFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return getRemoteScoreAndFill(id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }, service);

        // 等任务都执行完
        CompletableFuture all = CompletableFuture.allOf(userFuture, scoreFuture);

        System.out.println(".....阻塞");
//         阻塞住，等上面的2个任务执行完
        all.join();
        System.out.println("------阻塞结束-----------");

        TestMy.User2 user2 = userFuture.get();
        TestMy.UserScore userScore = scoreFuture.get();
        userInfo.setName(user2.getUserName());
        userInfo.setAge(user2.getUserAge());
        userInfo.setScore(userScore.getScore());

        System.out.println("=== userInfo === " + userInfo);

        long end = System.currentTimeMillis();
        long cost = end - start;
        System.out.println(">>>>>> cost = " + cost);
        return userInfo;
    }

    private TestMy.User2 getRemoteUserAndFill(long id) throws InterruptedException {
        long start = System.currentTimeMillis();
        // 模拟接口请求200毫秒
        Thread.sleep(3000);
        TestMy.User2 user2 = new TestMy.User2();
        user2.setUserName("zjy");
        user2.setUserAge(23);
        System.out.println("getRemoteUserAndFill id = " + id + " , time consuming is : " + (System.currentTimeMillis() - start));
        return user2;
    }

    private TestMy.UserScore getRemoteScoreAndFill(long id) throws InterruptedException {
        long start = System.currentTimeMillis();
        // 模拟接口请求耗时
        System.out.println("getRemoteScoreAndFill id = " + id);
        Thread.sleep(2600);
        TestMy.UserScore userScore = new TestMy.UserScore();
        userScore.setScore(99.8);
        if (id == 100)
            throw new RuntimeException("id = 100, xxxx异常");
        System.out.println("getRemoteScoreAndFill id = " + id + " , time consuming is : " + (System.currentTimeMillis() - start));
        return userScore;
    }

}
