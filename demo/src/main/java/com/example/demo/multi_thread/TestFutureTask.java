package com.example.demo.multi_thread;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import sun.security.provider.MD5;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestFutureTask {

    @Test
    public void test1() throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "123";
            }
        });

        futureTask.run();

        String v = futureTask.get();
        System.out.println("v >>> " + v);

        HashMap<String, String> map = new HashMap<>();
        String cc = map.putIfAbsent("q", "111");
        System.out.println("cc == " + cc);
    }

    @Test
    public void test2() {
        String v = "Pass12345";
        String md5 = stringToMd5(v);
        System.out.println("md5 >>> " + md5);
//        BeanFactory
    }

    public String stringToMd5(String psw) {
        {
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                md5.update(psw.getBytes("UTF-8"));
                byte[] encryption = md5.digest();

                StringBuffer strBuf = new StringBuffer();
                for (int i = 0; i < encryption.length; i++) {
                    if (Integer.toHexString(0xff & encryption[i]).length() == 1) {
                        strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                    } else {
                        strBuf.append(Integer.toHexString(0xff & encryption[i]));
                    }
                }

                return strBuf.toString();
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                return "";
            }
        }
    }
}
