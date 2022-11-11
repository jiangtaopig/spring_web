package com.example.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;

public class RemoveSessionListener implements ServletContextListener, HttpSessionListener {

    private static final Logger logger = LoggerFactory.getLogger(RemoveSessionListener.class);

    private final List<HttpSession> sessionList = Collections.synchronizedList(new LinkedList<>());

    private final Object lock = new Object();

    private Timer timer;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("------- 容器被创建啦 ---------");
        // 创建一个定时任务去看看 session 是否超过指定时间
        timer = new Timer();
        timer.schedule(new MyTask(sessionList, lock), 0, 10 * 1000);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("------- 容器被销毁啦 ---------");
        timer.cancel();
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.info("------- session 被创建啦 ---------");
        synchronized (lock) {
            sessionList.add(se.getSession());
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.info("------- session 被销毁啦 ---------");
    }
}
