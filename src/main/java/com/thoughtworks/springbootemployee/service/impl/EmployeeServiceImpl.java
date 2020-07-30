package com.thoughtworks.springbootemployee.service.impl;

import com.thoughtworks.springbootemployee.Dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
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
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    private List<Employee> employees = new ArrayList<>();

    public Employee getSpecificEmployee(int id) {
        return employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
    }

    public void addEmployees(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployees(int id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> getMaleEmployees(String gender) {
        return employeeRepository.findByGender(gender);
    }

    public void updateEmployees(Employee employee) {
        employeeRepository.save(employee);
    }


    public Page<Employee> pagingQueryEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public List<EmployeeResponse> getEmployees() {
        return employeeRepository.findAll().stream().map(employee -> {
            EmployeeResponse employeeResponse = new EmployeeResponse();
            employeeResponse.setId(employee.getId());
            employeeResponse.setName(employee.getName());
            employeeResponse.setGender(employee.getGender());
            return employeeResponse;
        }).collect(Collectors.toList());
    }
}
