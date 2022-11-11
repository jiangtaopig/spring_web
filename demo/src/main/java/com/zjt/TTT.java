package com.zjt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = CDPlayerConfig.class)
@ContextConfiguration("classpath:zjt.xml")
public class TTT {


    @Autowired
    TestInterfaceImpl testInterface;

    @Test
    public void test() {
        testInterface.doTest("哈哈哈");
    }
}
