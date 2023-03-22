package com.example.demo.cglib;

import sun.misc.Launcher;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class TestBookProxy {
    public static void main(String[] args) throws UnsupportedEncodingException {
        //sun.misc.ProxyGenerator.saveGeneratedFiles 用于输出代理类class文件到本地>> com.sun.proxy.xxx
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        IBook book = (IBook) new MyInvocationHandler().getMyProxy(new BookImpl());
        book.read("狼图腾");

        String ss = "OnePlus";
        if (ss.toUpperCase().equals("ONEPLUS")) {
            System.out.println("------------" + ss.toUpperCase());
        }

        String var1 = System.getProperty("java.class.path");
        System.out.println(" var1 >>>  " + var1);

        System.out.println("-------------------------");

        String a = "12345中";
        String b = URLEncoder.encode(a, "utf-8");
        System.out.println("b ="+b);

        String c = URLDecoder.decode("%7B%22appCode%22%3A%22APP04%22%2C%22deviceBrand%22%3A%22apple%22%2C%22deviceId%22%3A%22391DA4B9-6E69-401E-A943-A850E4DC3698%22%2C%22deviceModel%22%3A%22%E5%8D%8E%E4%B8%BAMate40%22%2C%22deviceSystem%22%3A%22iOS%22%2C%22mobile%22%3A%2213122222222%22%2C%22version%22%3A%223320107%22%7D", "utf-8");
        System.out.println("c = " + c);
//        Launcher : 是JRE中用于启动程序入口main()的类
        /**
         * 在虚拟机启动的时候会初始化BootstrapClassLoader，
         * 然后在Launcher类中去加载ExtClassLoader、AppClassLoader，并将AppClassLoader的parent设置为ExtClassLoader，
         * 并设置线程上下文类加载器。
         */
    }
}
