package com.dc.springbootapp1.service;

import com.dc.springbootapp1.common.dto.Employee;

import java.util.List;

public interface IEmployeeService {
    Employee getEmployeeById(String Id);

    void registerEmployee(com.dc.springbootapp1.common.domain.Employee employee);

    List<Employee> fetchAllEmployee();

    void sendMessage(Employee employee);
}
