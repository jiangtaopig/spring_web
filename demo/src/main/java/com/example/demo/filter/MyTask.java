package com.example.demo.filter;

import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

public class MyTask extends TimerTask {
    private final List<HttpSession> sessionList;
    private final Object lock;

    public MyTask(List<HttpSession> sessionList, Object lock) {
        this.sessionList = sessionList;
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            // 遍历容器
            System.out.println("开始检查 session 是否超过15s");
            Iterator<HttpSession>  iterator = sessionList.iterator();
            while (iterator.hasNext()) {
                HttpSession session = iterator.next();
                // 只要15秒没人使用，我就移除它啦
                if (System.currentTimeMillis() - session.getLastAccessedTime() > (1000 * 15)) {
                    System.out.println("session 被移除啦");
                    session.invalidate();
                    iterator.remove();
                }
            }
//            for (HttpSession session : sessionList) {
//                // 只要15秒没人使用，我就移除它啦
//                if (System.currentTimeMillis() - session.getLastAccessedTime() > (1000 * 15)) {
//                    System.out.println("session 被移除啦");
//                    session.invalidate();
//                    sessionList.remove(session);
//                }
//            }
        }
    }
}
