package com.example.demo.test_sqlite;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;


public class TestMyOrder {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void test() {
        SessionFactory factory;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        add(factory, "麻婆豆腐");
    }

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
        return id;
    }

    private void add2() {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        // 保存p对象
        MyOrder myOrder = new MyOrder("小炒黄牛肉");
        entityManager.persist(myOrder);
        tx.commit();
    }

    @Test
    public void testAdd2() {
        add2();
    }
}
