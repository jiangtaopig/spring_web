package com.example.demo.test_sqlite;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MyOrder")
public class MyOrder {
    @Id
    private int id;

    public MyOrder() {

    }

    public MyOrder(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
