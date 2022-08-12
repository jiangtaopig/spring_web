package com.example.demo.test_sqlite;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class TestHibernateWithSql {


    // Employee.hbm.xml 在 hibernate.cfg.xml 中注释了，执行的时候需要放开

    @Test
    public void test() {
        SessionFactory factory = null;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        ManageEmployee ME = new ManageEmployee(factory);
        /* Add few employee records in database */
        Integer empID1 = ME.addEmployee("Michal", "jason", 234);
//        Integer empID2 = ME.addEmployee("Daisy", "Das", 5000);
//        Integer empID3 = ME.addEmployee("John", "Paul", 10000);
        /* List down all the employees */
//        ME.listEmployees();
//        /* Update employee's records */
//        ME.updateEmployee(empID1, 5000);
//        /* Delete an employee from the database */
//        ME.deleteEmployee(empID2);
        /* List down new list of the employees */
        ME.listEmployees();
    }
}
