package com.thoughtworks.springbootemployee.service.impl;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    private List<Employee> employees = new ArrayList<>();

    public List<Employee> getEmployees() {
        return employees;
    }

    public Employee getSpecificEmployee(int id) {
        return employees.get(id);
    }

    public void addEmployees(Employee employee) {
        if (employees.stream()
                .filter(newEmployee -> employee.getId() == newEmployee.getId())
                .findFirst()
                .orElse(null) == null) {
            employees.add(employee);
        }
    }

    public void deleteEmployees(int id) {
        employees.remove(employees.stream()
                .filter(employee -> id == employee.getId())
                .findFirst()
                .orElse(null));
    }

    public List<Employee> getMaleEmployees(String gender) {
        List<Employee> maleEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getGender().equals("male")) {
                maleEmployees.add(employee);
            }
        }
        return maleEmployees;
    }

    public void updateEmployees(int id, Employee employee) {
        deleteEmployees(id);
        addEmployees(employee);
    }


    public Page<Employee> pagingQueryEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }
}