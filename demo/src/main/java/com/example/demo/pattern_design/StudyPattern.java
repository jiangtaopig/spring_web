package com.example.demo.pattern_design;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudyPattern {

    @Test
    public void testUnmodifiableList() {
        List<String> stringList = new ArrayList<String>() {{
            add("1");
            add("2");
            add("3");
        }};

        List<String> unModifyList = Collections.unmodifiableList(stringList);
        // 修改 unModifyList 抛出 UnsupportedOperationException 异常
//        unModifyList.add("4");


        List<Goods> goodsList = new ArrayList<Goods>() {{
                add(new Goods(new BigDecimal("22.5"), "拖鞋"));
                add(new Goods(new BigDecimal("999"), "NB1996r"));
            }};

        List<Goods> unmodifiableGoodList = Collections.unmodifiableList(goodsList);

        // 尽管 unmodifiableGoodList 是不可修改的，但是集合内部的数据却是可以修改的，如下：
        Goods goods = unmodifiableGoodList.get(1);
        goods.modifyPrice(new BigDecimal("1099"));

        System.out.println("goods = " + JSON.toJSONString(goods));

    }



    public static class Goods {
        private BigDecimal price;
        private String name;

        public Goods(BigDecimal price, String name) {
            this.price = price;
            this.name = name;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public String getName() {
            return name;
        }

        public void modifyPrice(BigDecimal price) {
            this.price = price;
        }
    }


}
