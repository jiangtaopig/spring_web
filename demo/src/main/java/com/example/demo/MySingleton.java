package com.example.demo;

public class MySingleton {

    private MySingleton() {
        System.out.println(" ----- MySingleton 构造函数------- ");
    }

    static  {
        System.out.println(" ----- MySingleton 初始化------- ");
    }

    {
        System.out.println(" ----- MySingleton 非静态代码块 初始化------- ");
    }

    public static class MySingletonHolder {

        private static final MySingleton singleton = new MySingleton();

        static {
            System.out.println(" >>>>>>>>>>> MySingletonHolder 初始化>>>>>>>>> singleton = " + singleton);
        }


    }

    public static MySingleton getSingleton() {
        return MySingletonHolder.singleton;
    }
}
