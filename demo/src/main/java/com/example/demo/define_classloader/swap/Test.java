package com.example.demo.define_classloader.swap;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        // 得到目标类的 class 对象
        Class<?> clazz = Class.forName("com.example.demo.define_classloader.swap.MyServiceImpl");
        // 得到目标对象
        /** 由于 MyServiceImpl 没有无参构造函数， 所以使用 clazz.newInstance() 实例化 MyServiceImpl 会报错,
         * 需要使用 constructor 来实例化 */
//        MyServiceImpl myService = (MyServiceImpl) clazz.newInstance();

        // 构造函数的参数类型是 (String , int)
        Constructor<?> constructor = clazz.getConstructor(String.class, int.class);
        // 通过构造器实例化 MyServiceImpl 对象，
        MyServiceImpl myService = (MyServiceImpl) constructor.newInstance("123", 88);
        // 实例化之后就可以调用方法了
        myService.doSth("xxx");

        MyTransactional myTransactional = clazz.getAnnotation(MyTransactional.class);
        if (myTransactional != null) {
            System.out.println("MyServiceImpl 类加上了 MyTransactional 注解");
        }
    }
}
