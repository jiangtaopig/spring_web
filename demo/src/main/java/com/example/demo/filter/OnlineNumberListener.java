package com.example.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 统计在线人数
 * 我们每使用一个浏览器访问服务器，都会新创建一个Session。那么网站的在线人数就会+1。
 * 比如，用谷歌浏览器打开 ：http://localhost:8180/demo_war_exploded/demo，后再使用 edge 浏览器打开，
 * 此时需要刷新下才会更新数据。
 * <p>
 * 使用同一个页面刷新，还是使用的是那个Sesssion，所以网站的在线人数是不会变的。
 */
public class OnlineNumberListener implements HttpSessionListener {
    static Logger logger = LoggerFactory.getLogger(OnlineNumberListener.class);
    public static final String ONLINE_NUMBER = "online_num";

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // 这个在线人数是整个站点的，所以应该有Context对象保存。
        // 获取得到Context对象，使用Context域对象保存用户在线的个数
        ServletContext context = se.getSession().getServletContext();
        //直接判断Context对象是否存在这个域，如果存在就人数+1,如果不存在，那么就将属性设置到Context域中
        Integer num = (Integer) context.getAttribute(ONLINE_NUMBER);

        if (num == null) {
            context.setAttribute(ONLINE_NUMBER, 1);
        } else {
            num++;
            context.setAttribute(ONLINE_NUMBER, num);
        }
        logger.info("sessionCreated >>>> num = " + num);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();
        Integer num = (Integer) context.getAttribute(ONLINE_NUMBER);

        if (num == null) {
            context.setAttribute(ONLINE_NUMBER, 1);
        } else {
            num--;
            context.setAttribute(ONLINE_NUMBER, num);
        }
        logger.info("sessionDestroyed >>>> num = " + num);
    }
}
