package com.example.demo;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TestDate {

    @Test
    public void test() {
        LocalDate date1 = LocalDate.parse("2023-03-11");
        LocalDate date2 = LocalDate.parse("2022-01-01");

        Period period = Period.between(date2, date1);
        System.out.println(period);
        int days = period.getDays();
        System.out.println(" days = " + days);


        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();


    }
}
