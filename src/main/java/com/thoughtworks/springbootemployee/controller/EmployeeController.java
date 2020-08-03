package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @GetMapping("/employees/{id}")
    public Employee getSpecificEmployee(@PathVariable("id") int id) {
        return employeeServiceImpl.getSpecificEmployee(id);
    }

    @PostMapping("/employees")
    public EmployeeResponse addEmployees(@RequestBody EmployeeRequest employee) {
        return employeeServiceImpl.addEmployees(employee);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployees(@PathVariable("id") int id) {
        employeeServiceImpl.deleteEmployees(id);
    }

    @PutMapping("/employees/{id}")
    public void updateEmployees(@PathVariable("id") int id,@RequestBody Employee employee) {
        employeeServiceImpl.updateEmployees(id,employee);
    }

    @GetMapping("/employees")
    public List<Employee> pagingQueryEmployees(@PageableDefault(size = 2) Pageable pageable, @RequestParam(defaultValue = "false", required = false) boolean unpaged) {
        if (unpaged) {
            return employeeServiceImpl.getEmployees();
        }
        return employeeServiceImpl.pagingQueryEmployees(pageable).getContent();
    }

    @GetMapping(value = "/employees", params = "gender")
    public List<Employee> getMaleEmployees(@RequestParam("gender") String gender) {
        return employeeServiceImpl.getMaleEmployees(gender);
    }
}

