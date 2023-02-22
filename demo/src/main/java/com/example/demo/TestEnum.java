package com.example.demo;

public class TestEnum {
    public static void main(String[] args) {
        String name = DepartmentEnum.getDepartmentName("SERVICE_DEPT");
        System.out.println("name = " + name);

        String a = "123\t\r";
        String b = a.trim();
        System.out.println("a "+a+"___");
        System.out.println("b = " +b);
    }
}
