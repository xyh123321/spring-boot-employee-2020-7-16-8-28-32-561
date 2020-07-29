package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();

    Employee getSpecificEmployee(int id);

    void addEmployees(Employee employee);

    void deleteEmployees(int id);

    List<Employee> getMaleEmployees(String gender);

    void updateEmployees(Employee employee);

    Page<Employee> pagingQueryEmployees(Pageable pageable);
}
