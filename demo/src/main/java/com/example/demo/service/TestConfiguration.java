package com.example.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyConfiguration.class)
public class TestConfiguration {

    @Qualifier("student1")
    @Autowired
    MyStudent student1;

    // 这样就会搜集 MyStudent 的集合
    @Autowired
    List<MyStudent> studentList;

    @Test
    public void testConfiguration() {
        System.out.println(">>>>>>>>>>>>>> " + student1);
        System.out.println(">>>>>> " + studentList);
    }
}
