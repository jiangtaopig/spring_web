package com.example.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestForEachRemove {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>(5);
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        strings.add("5");

        Iterator var2 = strings.iterator();

        while(var2.hasNext()) {
            String var3 = (String)var2.next();
            System.out.println("d = " + var3);
            if ("3".equals(var3)) {
                // 报 ConcurrentModificationException，因为 strings 中 remove 会导致 mod++,
                // 然后再次调用 var2.hastNext 会发现 mod 和 Iterator 中的 expectedModCount 不一致，所以报错
                // 应该改为 var2.remove();
                strings.remove(var3);
            }
        }

        System.out.println("list = " + strings);

//        for (String d : strings) {
//            System.out.println("d = " + d);
//            if ("3".equals(d)){
//                strings.remove(d);
//            }
//        }
    }
}
