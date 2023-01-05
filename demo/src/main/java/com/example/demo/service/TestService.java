package com.example.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:demo_sacn.xml")
@PropertySource({"classpath:application.properties"})
public class TestService {

    /**
     * 由于我在项目中有2个IDataService 的实现类 OracleService 和 SqlService，所以会报错：
     * No qualifying bean of type 'com.example.demo.service.IDataService' available:
     * expected single matching bean but found 2: oracleService,sqlService
     * <p>
     * 所以需要加上注解 Qualifier 来限定具体调用哪个
     */
    @Qualifier("innerService")
    // 使用 Component 、Repository (而 Repository 本身被 @Component 标记，所以它们都是间接标记了 @Componen) 等注解生成的 Bean ,
    // ID 默认是类名首字母小写
    @Autowired
    IDataService dataService;

    @Test
    public void dd() {
        dataService.showMsg("oracle service");
    }


    @Repository("innerService")
    public static class InnerDataService implements IDataService {

        @Override
        public void showMsg(String msg) {
            System.out.println("我是内部类的service >> " + msg);
        }
    }


}
