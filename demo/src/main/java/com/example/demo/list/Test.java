package com.example.demo.list;


import java.util.concurrent.TimeUnit;

public class Test {

    @org.junit.Test
    public void testArray() throws InterruptedException {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");

        arrayList.remove("1");

        TimeUnit.SECONDS.sleep(1);

        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            System.out.println("-------------------");
            String val = iterator.next();
            System.out.println("val = " + val);
        }

    }
}
