package com.example.demo.test_sqlite;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.List;

public class TestPersonSqlite {

    @Test
    public void test() {
        SessionFactory factory;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
//        addPerson(factory, "万宁宁", 24);
//        queryByName(factory, "万宁宁");
//        queryPerPage(factory);
//        sumOfAge(factory);
//        countOfAge(factory, 23);
//        avgOfAge(factory);

//        querySort(factory);
//        queryOrder2(factory);
//        addPersonBatch(factory);

//        update(factory, "猪小哥");
        delete(factory, "zhujiangtao");

    }

    public Integer addPerson(SessionFactory factory, String name, int age) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer id = null;
        try {
            tx = session.beginTransaction();
            Person person = new Person(name, age);
            id = (Integer) session.save(person);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return id;
    }

    public void queryByName(SessionFactory factory, String selectName) {
        String hql = "FROM Person E WHERE E.name = :mname"; // 字符占位要加冒号 (:)
        Session session = factory.openSession();
        try {
            Query query = session.createQuery(hql);
            query.setParameter("mname", selectName);
            List<Person> results = query.list();
            for (Person person : results) {
                System.out.println("name = " + person.getName());
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * 分页查询
     *
     */
    public void queryPerPage(SessionFactory factory) {
        String hql = "FROM Person";

        Session session = factory.openSession();
        try {
            Query query = session.createQuery(hql);
            query.setFirstResult(0); //该方法以一个整数表示结果中的第一行,从 0 行开始
            query.setMaxResults(3); // 这个方法告诉 Hibernate 来检索固定数量，即 每次取3条
            List<Person> results = query.list();
            for (Person person : results) {
                System.out.println("name = " + person.getName());
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Person 表中数据之和
     *
     */
    private void sumOfAge(SessionFactory factory) {
        Session session = factory.openSession();
        String hql = "SELECT sum (E.age) FROM Person E";

        try {
            Query query = session.createQuery(hql);
            String sum = query.uniqueResult().toString();
            System.out.println("sum = " + sum);

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private void maxOfAge(SessionFactory factory) {
        Session session = factory.openSession();
        String hql = "SELECT max (E.age) FROM Person E";

        try {
            Query query = session.createQuery(hql);
            String maxAge = query.uniqueResult().toString();
            System.out.println("maxAge = " + maxAge);

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * 统计 Person 表中 age 等于指定年龄的人数
     *
     */
    private void countOfAge(SessionFactory factory, int age) {
        Session session = factory.openSession();
//        String hqlString = "select count(*) from WaterPlan as p where p.planYear ='"+currYear+"'";
        String hql = "SELECT count (*) FROM Person E where E.age='" + age + "'";

        try {
            Query query = session.createQuery(hql);
            String count = query.uniqueResult().toString();
            System.out.println("count = " + count);

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * 表中年龄的平均值
     *
     */
    private void avgOfAge(SessionFactory factory) {
        Session session = factory.openSession();
        String hql = "SELECT avg (E.age) FROM Person E";

        try {
            Query query = session.createQuery(hql);
            String avg = query.uniqueResult().toString();
            System.out.println("avg = " + avg);

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * 排序
     *
     */
    public void querySort(SessionFactory factory) {
        // SELECT*FROM People ORDERBY FirstName DESC, YearOfBirth ASC
        // 第一个排序存在相同的值时，第二个排序才生效，否则第二个排序无任何效果。
        String hql = "FROM Person E ORDER BY E.age DESC "; // 降序排列
        Session session = factory.openSession();
        try {
            Query query = session.createQuery(hql);
            List<Person> results = query.list();
            for (Person person : results) {
                System.out.println("name = " + person.getName());
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void queryOrder2(SessionFactory factory) {
        // SELECT*FROM People ORDERBY FirstName DESC, YearOfBirth ASC
        // 第一个排序存在相同的值时，第二个排序才生效，否则第二个排序无任何效果。
        //
        String hql = "FROM Person E where E.name = '刘亦菲' ORDER BY E.age DESC "; // 降序排列
        Session session = factory.openSession();
        try {
            Query query = session.createQuery(hql);
            List<Person> results = query.list();
            for (Person person : results) {
                System.out.println("name = " + person.getName() + " , age = " + person.getAge());
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * update 要加入事务
     */
    public void update(SessionFactory factory, String name) {
        String hql = "UPDATE Person E SET E.name = '小诸葛' where E.name = '朱仁大'";
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery(hql);
//            query.setParameter("person_name", name);
            int result = query.executeUpdate();
//            System.out.println("Rows affected: " + result);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * delete 要加入事务
     */
    private void delete(SessionFactory factory, String name) {
        String hql = "DELETE Person E  where E.name = :person_name";
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("person_name", name);
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * 由于 Sqlite 不支持多连接，所以会报 The database file is locked
     */
    private void addPersonBatch(SessionFactory factory) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (int i = 0; i < 20; i++) {
                String name = "xiaoming" + i;
                int age = i + 10;
                Person person = new Person(name, age);
                session.save(person);
                // 需要在 hibernate.cfg.xml 中加入： <property name="hibernate.jdbc.batch_size">5</property>
                if (i % 10 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
