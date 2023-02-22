package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.UserInfo;
import com.example.demoapi.TestInterface;
import lombok.Data;
import lombok.Synchronized;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class TestMy {

    // 被@PostConstruct修饰的方法会在服务器加载Servle的时候运行，并且只会被服务器执行一次。
    // 需要类上面加注解 比如 @Component ， @Service 等
    @PostConstruct
    public void init() {
        System.out.println("-------  TestMy init ---------");
    }

    public void TestMy() {
        System.out.println("-------  TestMy construct ---------");
    }

    @Test
    public void testH() {
        Logger logger = LoggerFactory.getLogger(TestMy.class);
        logger.info("xxxx");
        System.out.println("------testH----------------");

        String jsonS = "{\n" +
                "      \"viewAttr\": {\n" +
                "        \"expandStatus\": \"true\",\n" +
                "        \"expandFlag\": \"display1\",\n" +
                "        \"textList\": [\"损失金额\",\"#lossPrice#\"],\n" +
                "        \"titleColor\": \"0xff666768\",\n" +
                "        \"type\": \"label\"\n" +
                "      },\n" +
                "      \"params\": null,\n" +
                "      \"actions\": null\n" +
                "    }";

        JSONObject templateRow = JSONObject.parseObject(jsonS);

        UserInfo userInfo = new UserInfo();

        userInfo.setAge(23);
        userInfo.setName("zz");
        userInfo.setUserId("11011");

        UserInfo.Address address = new UserInfo.Address();
        address.setRoom(601);
        address.setStreet("北新泾街道");

        userInfo.setAddress(address);

        String personStr = JSONObject.toJSONString(userInfo);

        System.out.println("personStr = " + personStr);

        UserInfo userInfo1 = JSONObject.parseObject(personStr, UserInfo.class);

        System.out.println("name = " + userInfo1.getName());

        String aa = templateRow.toJSONString();

        System.out.println("aa = " + aa);

        if (jsonS.contains("损失金额")) {
            JSONObject attr = (JSONObject) templateRow.get("viewAttr");


            List list = (List) attr.get("textList");
            list.set(0, "损失面数");
            attr.put("textList", list);
            templateRow.put("viewAttr", attr);
            System.out.println("-------------------------");
        }

        // 测试 修改 commit msg

        AlarmInfoPlate alarmInfoPlate = new AlarmInfoPlate();
        alarmInfoPlate.setDeviceName("VM00891_01");

        PlateResult plateResult = new PlateResult();
        plateResult.setLicense("llxxxxssss");

        Map<String, PlateResult> map = new HashMap<>();
        map.put("PlateResult", plateResult);

        alarmInfoPlate.setResult(map);

        String alarmJson = JSONObject.toJSONString(alarmInfoPlate);
        System.out.println("=============================================================");
        System.out.println("alarmJson = " + alarmJson);
        System.out.println("=============================================================");

        String nn = "hhh";
//        test2(nn);
        System.out.println("nn = " + nn);

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");

        String now = simpleDateFormat.format(date);
        System.out.println("now = " + now);

        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, -24);

        String late = simpleDateFormat.format(calendar.getTime());

        System.out.println("late = " + late);


    }

    @Test
    public void test3() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.putIfAbsent("1", "a");

        System.out.println("1 ----> " + map.get("1"));
        map.computeIfPresent("33", (k, v) -> {
            return k + v;
        });
        System.out.println("1 ----> vvv " + map.get("33"));
//        map.computeIfAbsent()
        String data = map.computeIfAbsent("m", k -> k + "3");

        System.out.println("data === > " + data + ", map = " + map);

        String d = map.computeIfAbsent("m", k -> k + "_2");
        System.out.println("d >>> " + d);
    }

    @Test
    public void testStopWatch() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        // 任务一模拟休眠3秒钟
        stopWatch.start("TaskOneName");
        Thread.sleep(1000 * 3);
        System.out.println("当前任务名称：" + stopWatch.currentTaskName());
        stopWatch.stop();

//        // 任务一模拟休眠10秒钟
//        stopWatch.start("TaskTwoName");
//        Thread.sleep(1000 * 10);
//        System.out.println("当前任务名称：" + stopWatch.currentTaskName());
//        stopWatch.stop();
//
//        // 任务一模拟休眠10秒钟
//        stopWatch.start("TaskThreeName");
//        Thread.sleep(1000 * 10);
//        System.out.println("当前任务名称：" + stopWatch.currentTaskName());
//        stopWatch.stop();

        // 打印出耗时
        System.out.println(stopWatch.prettyPrint());
        System.out.println(stopWatch.shortSummary());
//        // stop后它的值为null
//        System.out.println(stopWatch.currentTaskName());
//
//        // 最后一个任务的相关信息
//        System.out.println(stopWatch.getLastTaskName());
//        System.out.println(stopWatch.getLastTaskInfo());
//
//        // 任务总的耗时 如果你想获取到每个任务详情（包括它的任务名、耗时等等）可使用
//        System.out.println("所有任务总耗时：" + stopWatch.getTotalTimeMillis());
//        System.out.println("任务总数：" + stopWatch.getTaskCount());
//        System.out.println("所有任务详情：" + stopWatch.getTaskInfo());

    }

    @Test
    public void testString() {
        String s1 = "abc";
        final String s2 = "ab";
        final String s3 = "c";
        String s4 = s2 + s3;

        System.out.println("s1 == s4 " + (s1 == s4));

        String ss1 = new String("11").intern();
        String ss2 = "11";

        System.out.println("ss1 == ss2 " + (ss1 == ss2));

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);


        String result = "11#lossType#22";
        String lossType = null;
        result = result.replace("#lossType#", lossType);

//        BigDecimal bigDecimal = new BigDecimal("");
        int a = 1;
        System.out.println("aaaaaaaaaaaaaaaaaaaaa");
    }

    @Test
    public void testLock() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        ReentrantLock lock = new ReentrantLock();

//        lock.tryLock(); // tryLock 获取锁，如果发现锁以及被其他线程获取了，直接返回 false，不会去等待

        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ">>> start -----");
            try {
                lock.lock();
                TimeUnit.SECONDS.sleep(8);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                latch.countDown();
            }
            System.out.println(Thread.currentThread().getName() + ">>> end -----");

        }, "thread--1");

        thread1.start();

//        TimeUnit.SECONDS.sleep(1);

        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "--- start ---");
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                System.out.println("---- " + e.toString());
            } finally {
                System.out.println("fff");
            }
            System.out.println(Thread.currentThread().getName() + "--- end ---");
        }, "thread2");

        thread2.start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("执行中断------------");
        thread2.interrupt();

        System.out.println(Thread.currentThread().getName() + "执行 await ");
        latch.await();
        System.out.println(Thread.currentThread().getName() + "执行 await 完成");
    }

    @Test
    public void testUserInfo() {

        long start = System.currentTimeMillis();
        try {
            UserInfo2 userInfo2 = getUserInfo();
            System.out.println("name = " + userInfo2.getName() + ", score = " + userInfo2.getScore());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("time consuming >>> " + (System.currentTimeMillis() - start));
    }

    @Test
    public void testWaitAndNotify() throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(1);
        Object lock = new Object();

        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ">>> start -----");

            try {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + " --- wait ---");
                    lock.wait();
                    System.out.println(Thread.currentThread().getName() + " --- 被唤醒 ---");
                    countDownLatch.countDown();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ">>> end -----");

        }, "thread--1");

        Thread thread3 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ">>> start -----");

            try {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + " --- wait ---");
                    lock.wait();
                    System.out.println(Thread.currentThread().getName() + " --- 被唤醒 ---");
                    countDownLatch.countDown();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ">>> end -----");

        }, "thread--1");

        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ">>> start -----");

            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " --- 调用 notify ---");
                // notify 不会立即换新 之前执行 wait 的线程，需要等此线程执行完成
                lock.notify();
                System.out.println(Thread.currentThread().getName() + " --- sleep  ---");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " --- sleep over  ---");
            }
            System.out.println(Thread.currentThread().getName() + ">>> end -----");

        }, "thread--2");

        thread1.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.start();

        countDownLatch.await();

        System.out.println("---------------------- end ---------------------");
    }

    @Test
    public void testThreadPool() {
        ExecutorService executorService = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), new ThreadPoolExecutor.CallerRunsPolicy());

        // 使用 CallerRunsPolicy 这种策略，那么被抛弃的任务会直接在调用的线程中进行处理

        for (int i = 0; i < 4; i++) {
            int finalI = i;
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " , task  " + finalI + " start");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " , task  " + finalI + " end");
            });
        }
    }


    @Test
    public void testException() {
        try {
            testException2();
        } catch (Exception e) {
            System.out.println("出错啦 ！！！");
            e.printStackTrace();
        }
    }

    private void testException2() {
        try {
            int a = 10 / 0;
        } catch (ArithmeticException e) {
            throw new RuntimeException("系统运行异常", e);
        }
    }

    @Test
    public void testReplace() {
        String content = "{\"content\":\"{\\n    \\\"top\\\":{\\n        \\\"center\\\":{\\n        " +
                "    \\\"viewAttr\\\":{\\n                \\\"title\\\":\\\"扫码核销\\\"\\n            }\\n        }\\n    }," +
                "\\n    \\\"extraMap\\\":{\\n\\t\\t\\\"vouchersName\\\":\\\"#vouchersName#\\\",\\n\\t\\t      " +
                "  \\\"dealerCode\\\":\\\"#dealerCode#\\\",\\n\\t\\t        \\\"currentStep\\\":\\\"#currentStep#\\\",\\n\\t\\t   " +
                "     \\\"templateNo\\\":\\\"#templateNo#\\\",\\n\\t\\t        \\\"settlementId\\\":\\\"#settlementId#\\\",\\n\\t\\t   " +
                "     \\\"businessType\\\":\\\"#businessType#\\\",\\n\\t\\t        \\\"writeCode\\\":\\\"#writeCode#\\\",\\n\\t\\t     " +
                "   \\\"productCategory\\\":\\\"#productCategory#\\\",\\n\\t\\t\\t\\t\\\"productNo\\\":\\\"#productNo#\\\",\\n\\t\\t\\t\\t  " +
                " \\\"accountBankCode\\\": \\\"#accountBankCode#\\\",\\n\\t\\t\\t\\t   \\\"accountName\\\": \\\"#accountName#\\\",\\n\\t\\t\\t\\t  " +
                " \\\"accountNo\\\": \\\"#accountNo#\\\",\\n\\t\\t\\t\\t   \\\"subBranch\\\": \\\"#subBranch#\\\",\\n\\t\\t\\t\\t  " +
                " \\\"accountBankProvince\\\": \\\"#accountBankProvince#\\\",\\n\\t\\t\\t\\t   " +
                "\\\"accountBankCity\\\": \\\"#accountBankCity#\\\",\\n\\t\\t\\t\\t   " +
                "\\\"accountBankDistrict\\\": \\\"#accountBankDistrict#\\\",\\n\\t\\t\\t\\t   " +
                "\\\"firstMaintainDate\\\":\\\"#firstMaintainDate#\\\",\\n\\t\\t\\t\\t   " +
                "\\\"firstMileage\\\":\\\"#firstMileage#\\\"\\n    },\\n    \\\"bottom\\\":[\\n        {\\n            \\\"viewAttr\\\":{\\n                \\\"firstButtonType\\\":\\\"primary\\\",\\n                \\\"firstButtonTitle\\\":\\\"下一步\\\",\\n                \\\"type\\\":\\\"button\\\"\\n            },\\n            \\\"actions\\\":[\\n                 {\\n                     \\\"type\\\":\\\"submit\\\",\\n                     \\\"url\\\":\\\"app/writeoff/stageInTemplate\\\",\\n                     \\\"next-step\\\":{\\n                         \\\"type\\\":\\\"link\\\",\\n                         \\\"url\\\":\\\"writeOffScanCommonTemplate\\\"\\n                     }\\n                 }\\n            ]\\n        }\\n    ],\\n    \\\"content\\\":[\\n        {\\n            \\\"viewAttr\\\":{\\n                \\\"type\\\":\\\"divider\\\"\\n            }\\n        },\\n        {\\n            \\\"viewAttr\\\":{\\n                \\\"textList\\\":[\\n                    \\\"#vouchersName#\\\"\\n                ],\\n                \\\"titleColor\\\":\\\"0xff060606\\\",\\n                \\\"titleFontWeight\\\":\\\"bold\\\",\\n                \\\"type\\\":\\\"label\\\"\\n            },\\n            \\\"actions\\\":[\\n                {\\n\\n                }\\n            ]\\n        },\\n        {\\n            \\\"viewAttr\\\":{\\n                \\\"type\\\":\\\"divider\\\"\\n            }\\n        },\\n        {\\n            \\\"viewAttr\\\":{\\n                \\\"isRequired\\\":\\\"Y\\\",\\n                \\\"keyboardType\\\":\\\"province\\\",\\n                \\\"type\\\":\\\"text_field\\\",\\n                \\\"title\\\":\\\"车牌号\\\"\\n            },\\n            \\\"params\\\":[\\n                {\\n                    \\\"value\\\":\\\"true\\\",\\n                    \\\"key\\\":\\\"requiredMap.licenseNo\\\"\\n                },\\n                {\\n                    \\\"value\\\":\\\"#licenseNo#\\\",\\n                    \\\"key\\\":\\\"licenseNo\\\"\\n                }\\n            ]\\n        },\\n        {\\n            \\\"viewAttr\\\":{\\n                \\\"textList\\\":[\\n                    \\\"车架号\\\",\\n                    \\\"#vin#\\\"\\n                ],\\n                \\\"titleColor\\\":\\\"0xff666768\\\",\\n                \\\"type\\\":\\\"label\\\"\\n            },\\n            \\\"actions\\\":null\\n        },\\n{\\n            \\\"viewAttr\\\":{\\n              \\\"isRequired\\\":\\\"Y\\\",\\n              \\\"rightUrl\\\":\\\"local:images/icon/calendar.png\\\",\\n              \\\"format\\\":\\\"ymd\\\",\\n              \\\"type\\\":\\\"time_picker\\\",\\n              \\\"title\\\":\\\"进店保养日期\\\"\\n            },\\n            \\\"params\\\":[\\n              {\\n                    \\\"value\\\":\\\"true\\\",\\n                    \\\"key\\\":\\\"requiredMap.storeMaintainDate\\\"\\n              },\\n              {\\n                \\\"value\\\":\\\"#storeMaintainDate#\\\",\\n                \\\"key\\\":\\\"storeMaintainDate\\\"\\n              }\\n            ]\\n          },  \\n{\\n            \\\"viewAttr\\\":{\\n                \\\"isRequired\\\":\\\"Y\\\",\\n                \\\"keyboardType\\\":\\\"phone\\\",\\n                \\\"type\\\":\\\"text_field\\\",\\n                \\\"title\\\":\\\"进店里程数\\\"\\n            },\\n            \\\"params\\\":[\\n                {\\n                    \\\"value\\\":\\\"true\\\",\\n                    \\\"key\\\":\\\"requiredMap.storeMileage\\\"\\n                },\\n                {\\n                    \\\"value\\\": \\\"#storeMileage#\\\",\\n                    \\\"key\\\":\\\"storeMileage\\\"\\n                }\\n            ]\\n        },\\\"#firstMaintainDateContent#\\\",\\\"#firstMileageContent#\\\"\\n       \\n        \\n    ]\\n}\",\"createTime\":1663048488000,\"createUser\":\"appadmin\",\"id\":33,\"md5\":\"d90e3a879ad3c0cb6bb31f5a61ced66a\",\"step\":\"STEP1\",\"templateNo\":\"NON_AUTO_002\",\"updateTime\":1663061695000,\"updateUser\":\"appadmin\"}\n";
        String pattern = "#.*?#";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(content);

        while (m.find()) {
            String originKeyword = m.group();
            String replaceKeyword = originKeyword.replace("#", "");

            String a = "xxx";
        }
    }

    @Test
    public void testBlockingQueue() {
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(1);
        int times = 10;
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < times; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + " ------- take ------");
                    int value = arrayBlockingQueue.take();
                    System.out.println(Thread.currentThread().getName() + " >>> value = " + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "consumer-thread");


        Thread producer = new Thread(() -> {
            for (int i = 0; i < times; i++) {
                System.out.println(Thread.currentThread().getName() + " before put .... " + i);
                try {
                    arrayBlockingQueue.put(i);
                    System.out.println(Thread.currentThread().getName() + " after put .... " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "producer-thread");

        consumer.start();
        producer.start();
    }


    @Test
    public void testCompletableFuture2() throws ExecutionException, InterruptedException {
        getUserInfo();
    }

    /**
     * 使用 CompletableFuture 来异步执行多个接口，获取接口的执行结果
     */
    public UserInfo2 getUserInfo() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ExecutorService service = new ThreadPoolExecutor(3, 5, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5));

        // 现在想要获取 用户信息，但是用户基础信息和分数信息不在同一个接口，就可以如下去处理
        UserInfo2 userInfo = new UserInfo2();
        long id = 10010;

        CompletableFuture<User2> userFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return getRemoteUserAndFill(id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }, service);

        CompletableFuture<UserScore> scoreFuture = CompletableFuture.supplyAsync(() -> {
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

        // 阻塞住，等上面的2个任务执行完
        all.join();

        System.out.println("------阻塞结束-----------");


        User2 user2 = userFuture.get();
        UserScore userScore = scoreFuture.get();
        userInfo.setName(user2.getUserName());
        userInfo.setAge(user2.getUserAge());
        userInfo.setScore(userScore.getScore());

        System.out.println("=== userInfo === " + userInfo);

        long end = System.currentTimeMillis();
        long cost = end - start;
        System.out.println(">>>>>> cost = " + cost);
        return userInfo;
    }

    @Test
    public void test4() throws InterruptedException {
        System.out.println("--------- start ---------------");
        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        CompletableFuture<User2> user2CompletableFuture = CompletableFuture.supplyAsync(() -> {
            return getUser2();
        }).whenCompleteAsync((user2, throwable) -> {
            System.out.println("name = " + user2.userName + ", age = " + user2.userAge);
            countDownLatch.countDown();
        });
//                .thenAcceptAsync(user2 -> {
//            System.out.println("name = "+user2.userName+", age = " + user2.userAge);
//        });

//        user2CompletableFuture.join();
        // 不适用 countDownLatch 那么就得使用 user2CompletableFuture 的 join 或者 get 方法，以用来阻塞当前线程，直到任务完成
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("------------------------------ cost ------" + (end - start));
    }


    @Test
    public void testGetUser2() {
        long start = System.currentTimeMillis();
        User2 user2 = test5();
        long end = System.currentTimeMillis();

        System.out.println("testGetUser2 >>>>> cost time = " + (end - start) + " , user2 = " + user2);
    }

    public User2 test5() {
        CompletableFuture<User2> userFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return getRemoteUserAndFill(111);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });

        /**
         * 可以做的接口时间限制，即2秒内没返回就报异常
         */
        User2 user2 = null;
        try {
            user2 = userFuture.get(4, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            if (e instanceof TimeoutException) {
                System.out.println("-----------超时了-----------");
                user2 = new User2();
                user2.userAge = 11;
                user2.userName = "TimeoutException";
            }
        }
        return user2;
    }

    private User2 getUser2() {
        long start = System.currentTimeMillis();
        // 模拟接口请求200毫秒
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        User2 user2 = new User2();
        user2.setUserName("zjy");
        user2.setUserAge(23);
        System.out.println("getUser2 " + ", time consuming is : " + (System.currentTimeMillis() - start));
        return user2;
    }

    @Test
    public void testCompletableFuture() {
        System.out.println("---------------- start -----------------");
        long start = System.currentTimeMillis();
        CompletableFuture<UserInfo2> userScoreCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return getRemoteUserAndFill(101);
            } catch (InterruptedException e) {
                System.out.println("异常2 ....");
                e.printStackTrace();
            }
            return null;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                return getRemoteScoreAndFill(101);
            } catch (InterruptedException e) {
                System.out.println("异常1 ....");
                e.printStackTrace();
            }
            return null;
        }), (a, b) -> {
            System.out.println("a-----------------" + a + ", b = " + b);
            String name = a.userName;
            int age = a.userAge;
            double score = b.getScore();

            UserInfo2 combine = new UserInfo2();
            combine.setName(name);
            combine.setAge(age);
            combine.setScore(score);
            return combine;
        }).exceptionally(throwable -> {
            System.out.println("异常了.................");
            return null;
        });

//         get方法和join方法功能一样，就是没有编译验证，不需要抛出异常
        UserInfo2 userInfo = userScoreCompletableFuture.join();

        System.out.println("userInfo = " + userInfo);

        try {
            UserInfo2 userInfo2 = userScoreCompletableFuture.get();
            System.out.println("userInfo2 = " + userInfo2);
            long cost = System.currentTimeMillis() - start;
            System.out.println("总耗时 >>>> ：" + cost);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("---------------- end --------------------");
    }

    private User2 getRemoteUserAndFill(long id) throws InterruptedException {
        long start = System.currentTimeMillis();
        // 模拟接口请求200毫秒
        Thread.sleep(3000);
        User2 user2 = new User2();
        user2.setUserName("zjy");
        user2.setUserAge(23);
        System.out.println("getRemoteUserAndFill id = " + id + " , time consuming is : " + (System.currentTimeMillis() - start));
        return user2;
    }

    private UserScore getRemoteScoreAndFill(long id) throws InterruptedException {
        long start = System.currentTimeMillis();
        // 模拟接口请求耗时
        System.out.println("getRemoteScoreAndFill id = " + id);
        Thread.sleep(2600);
        UserScore userScore = new UserScore();
        userScore.setScore(99.8);
        if (id == 100)
            throw new RuntimeException("id = 100, xxxx异常");
        System.out.println("getRemoteScoreAndFill id = " + id + " , time consuming is : " + (System.currentTimeMillis() - start));
        return userScore;
    }


    @Test
    public void testJavaStream() {
        UserInfo2 info1 = new UserInfo2();
        info1.setName("张三");
        info1.setAge(20);
        info1.setScore(98);

        UserInfo2 info2 = new UserInfo2();
        info2.setName("李四");
        info2.setAge(22);
        info2.setScore(89);

        UserInfo2 info3 = new UserInfo2();
        info3.setName("王五");
        info3.setAge(19);
        info3.setScore(92);

        List<UserInfo2> userInfo2List = Arrays.asList(info1, info2, info3);

        List<UserInfo2> userInfo2s = userInfo2List.stream().filter(userInfo2 -> {
            return userInfo2.getScore() > 90;
        }).collect(Collectors.toList());

        System.out.println("userInfo2s = " + userInfo2s);

        // 分数最高的用户

        Optional<UserInfo2> maxScoreUser = userInfo2List.stream().max(Comparator.comparingDouble(UserInfo2::getScore));

        System.out.println("maxScoreUser = " + maxScoreUser.get());
    }
    


    /**
     * 测试等3个任务都完成了再执行其他逻辑
     */
    @Test
    public void testAllDone() throws InterruptedException {
        System.out.println("start >>> " + System.currentTimeMillis());
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            return getTask1Result();
        });

        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
            return getTask2Result();
        });

        CompletableFuture<String> task3 = CompletableFuture.supplyAsync(() -> {
            return getTask3Result();
        });

         CompletableFuture.allOf(task1, task2, task3)
                 .whenComplete((res , ex) -> {
             System.out.println("res = " + res);
             System.out.println("end >>> " + System.currentTimeMillis());
                     try {
                         System.out.println("task3 result >>> " + task3.get());
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     } catch (ExecutionException e) {
                         e.printStackTrace();
                     }
                 });

         Thread.sleep(5000);
        System.out.println("----- end -----");
    }

    /**
     * 测试只要有一个任务完成就返回
     */
    @Test
    public void testAnyDone() throws ExecutionException, InterruptedException {
        System.out.println("start >>> " + System.currentTimeMillis());
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            return getTask1Result();
        });

        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
            return getTask2Result();
        });

        CompletableFuture<String> task3 = CompletableFuture.supplyAsync(() -> {
            return getTask3Result();
        });


        CompletableFuture<Object> anyTask = CompletableFuture.anyOf(task1, task2, task3)
                .whenComplete((res , ex) -> {
                    System.out.println("res = " + res);
                    System.out.println("end >>> " + System.currentTimeMillis());
                    try {
                        System.out.println("task1 result >>> " + task1.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                });

        anyTask.get();
        System.out.println("----- end -----");
    }

    private String getTask1Result() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "任务1完成";
    }

    private String getTask2Result() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "任务2完成";
    }

    private String getTask3Result() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "任务3完成";
    }

    @Test
    public void tt() {
        CompletableFuture<User2> userFuture = CompletableFuture.supplyAsync(new Supplier<User2>() {
            @Override
            public User2 get() {
                try {
                    return getRemoteUserAndFill(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });

        ExecutorService service = new ThreadPoolExecutor(2, 4, 60,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(5));
        Future<Long> future = service.submit(new MyTask());

        System.out.println("阻塞前.............");

        try {
            // 阻塞主线程，直到线程池中的任务执行完毕
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("主线程结束.............");
        int a;
        int b = 6;
    }


    @Test
    public void testCompletableWithDefineThreadPool () {
        ExecutorService service = new ThreadPoolExecutor(2, 4, 60,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(5));

        System.out.println("----------- main start time >>> -----------" + System.currentTimeMillis());

        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            String res = getTask1Result();
            System.out.println(" res  >>>> " + res);
            return res;
        }, service);

        task1.join();

        System.out.println("----------- main end time -----------" + + System.currentTimeMillis());
    }

    /** -------------------------------------------------------- model 定义 -------------------------------------------------------- **/

    static class MyTask implements Callable<Long> {
        @Override
        public Long call() throws Exception {
            System.out.println("开始执行任务 time >>> " + System.currentTimeMillis());
            long a = 100;
            Thread.sleep(2000);
            System.out.println("任务执行结束 time >>> " + System.currentTimeMillis());
            return a;
        }
    }

    @Data
    static class UserInfo2 {
        private String name;
        private int age;
        private double score;
    }

    @Data
    static class User2 {
        String userName;
        int userAge;
    }

    @Data
    static class UserScore {
        double score;
    }

    @Data
    static class UserCombine {
        private String name;
        private int age;
        private double score;
    }

    static class AlarmInfoPlate {
        private String deviceName;
        private Map<String, PlateResult> result = new HashMap<>();

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public Map<String, PlateResult> getResult() {
            return result;
        }

        public void setResult(Map<String, PlateResult> result) {
            this.result = result;
        }
    }

    static class PlateResult {
        private String license;

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }
    }
}
