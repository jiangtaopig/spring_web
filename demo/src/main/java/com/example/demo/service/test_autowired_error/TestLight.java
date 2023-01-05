package com.example.demo.service.test_autowired_error;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:demo_sacn.xml")
public class TestLight {

    @Autowired
    LightManager lightManager;

    @Test
    public void testLight() {
        lightManager.start();
    }
}
