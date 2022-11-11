package com.example.dubboprovider;

import java.io.IOException;

import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Hello world!
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class App 
{
	@SuppressWarnings("resource")
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "dubbo provider start!" );
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");  
        context.start();  
        System.in.read(); // 为保证服务一直开着，利用输入流的阻塞来模拟
    }
}
