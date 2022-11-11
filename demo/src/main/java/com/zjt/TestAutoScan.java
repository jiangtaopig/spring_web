package com.zjt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


/**
 * SpringJUnit4ClassRunner 会在测试开始的时候创建 Spring 上下文
 * ContextConfiguration 告诉他需要在 CDPlayerConfig 中加载配置
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDPlayerConfig.class)
public class TestAutoScan {

    @Resource(name = "cdPlayer")
    CDPlayer cdPlayer;

    @Autowired
    MyFactory myFactory;

    @Test
    public void cdPlayer() {
//        CDPlayer cdPlayer = new CDPlayer();
        cdPlayer.play();
//        #{cdPlayer.play()};
    }

    @Test
    public void testFactory() {
        myFactory.doSth("WriteOff");
    }
}
