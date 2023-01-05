package com.example.demo.test_sqlite;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SqlConfig.class)
public class TestSqlService {


    @Test
    public void testTransactional() {
        SessionFactory factory;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        // 尽管报错了还是会把第一条数据 ("小炒黄牛肉") 插入
        add(factory, "鸡肉");
    }


    @Transactional(rollbackFor = Exception.class)
    public Integer add(SessionFactory factory, String title) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer id = null;
        try {
            tx = session.beginTransaction();
            MyOrder myOrder = new MyOrder(title);
            id = (Integer) session.save(myOrder);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        if ("鸡肉".equals(title)) {
            throw new RuntimeException("菜已存在");
        }
        return id;
    }
}
