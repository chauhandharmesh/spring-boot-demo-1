package com.dc.springbootapp1.common.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document("employee")
public class Employee {
    @Id
    private String id;
    private String name;
    private String department;
    private BigDecimal salary;
    private int yearsOfExperience;
    private LocalDateTime updatedTime = LocalDateTime.now();

    public Employee() {
    }

    public Employee(String id, String name, String department, BigDecimal salary, int yearsOfExperience, LocalDateTime updatedTime) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.yearsOfExperience = yearsOfExperience;
        this.updatedTime = updatedTime;
    }

    public Employee(com.dc.springbootapp1.common.dto.Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.department = employee.getDepartment();
        this.salary = employee.getSalary();
        this.yearsOfExperience = employee.getYearsOfExperience();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }
}
