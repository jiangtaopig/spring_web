package com.example.demo.stream;

import lombok.Data;
import org.junit.Test;

import java.util.Optional;

public class TestOptional {


    @Test
    public void test1() {
        Optional<Employee> employeeOptional = Optional.ofNullable(getEmployee());


        Employee employee = employeeOptional.get();

        System.out.println("employee >>> " + employee);

       String name = Optional.ofNullable(getNullEmployee())
                .map(employee1 -> {
                    System.out.println("employee1 >>> " +employee1);
                    return employee1.name;
                }).orElse("xxx");
        System.out.println("name  >>>> "+name);

        Optional<Team> optionalTeam = Optional.ofNullable(getEmployee())
                .map(employee1 -> {
                    return employee1.team;
                });
        Team team = optionalTeam.get();
        System.out.println("team  >>> " + team);


        String title = Optional.ofNullable(getEmployee())
                .map(employee1 -> {
                    System.out.println("------------ title >>> " + employee1.team.title);
                    return employee1.team.title;
                }).orElse("no title");

        String a = "";
        Long b = Long.valueOf(a);
        System.out.println(b);
    }

    public Employee getEmployee() {
        Employee employee = new Employee();
        employee.name = "pig";
        Team team = new Team();
        team.department = "app研发";
        team.title = "工程师";

        employee.team = team;
        return employee;
    }

    public Employee getNullEmployee() {
        return null;
    }


    @Data
    static class Employee {
        private String name;
        private Team team;
    }

    @Data
    static class Team {
        private String department;
        private String title;
    }
}
