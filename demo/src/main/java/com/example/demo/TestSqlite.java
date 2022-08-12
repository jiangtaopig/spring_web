package com.example.demo;


import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestSqlite {

    @Test
    public void test() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            //这一行要使用我们搭建SQLite时的url
            c = DriverManager.getConnection("jdbc:sqlite:D:\\Sping_Web\\dd3\\identifier.sqlite");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            //包括这里执行的查询语句也需要根据你建立的表格来决定
            stmt.execute("insert into Student values ('333','pig');");
            ResultSet rs = stmt.executeQuery("SELECT * FROM Student;");

            while (rs.next()) {
                String Num = rs.getString("Num");
                String Name = rs.getString("Name");
                System.out.println("我的学号是： " + Num);
                System.out.println("我的姓名是： " + Name);
            }
            rs.close();
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
}
