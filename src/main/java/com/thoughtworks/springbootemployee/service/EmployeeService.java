package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();

    Employee getSpecificEmployee(int id);

    void addEmployees(Employee employee);

    void deleteEmployees(int id);

    List<Employee> getMaleEmployees(String gender);

    void updateEmployees(int id, Employee employee);

    List<Employee> pagingQueryEmployees(int page, int pageSize);
}
