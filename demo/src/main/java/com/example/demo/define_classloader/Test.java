package com.example.demo.define_classloader;


import com.example.demo.define_classloader.swap.MyBean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class Test {


    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        String swapPath = MyClassLoader.class.getResource("").getPath()+"swap/";
        System.out.println("swapPath >>> " + swapPath);

        String className = "com.example.demo.define_classloader.swap.MyBean";

        Set<String> set = new HashSet<>();
        set.add(className);

        MyClassLoader myClassLoader = new MyClassLoader(swapPath, set);

        Class<MyBean> clazz = (Class<MyBean>) myClassLoader.loadClass(className);
        Object instance = clazz.newInstance();

        Method [] methods = instance.getClass().getMethods();
        for (Method method : methods) {
            System.out.println("method name >> " + method.getName());
        }

        instance.getClass().getDeclaredMethod("printVersion").invoke(instance);
        int a = (int) instance.getClass().getDeclaredMethod("getMax", int.class).invoke(instance, 2);

        System.out.println("a = " + a);
    }
}
