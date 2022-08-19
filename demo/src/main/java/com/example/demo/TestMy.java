package com.example.demo;

import com.example.demo.filter.MyFilter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMy {

    @Test
    public void testH() {
        Logger logger = LoggerFactory.getLogger(TestMy.class);
        logger.info("xxxx");
        System.out.println("------testH----------------");
    }
}
