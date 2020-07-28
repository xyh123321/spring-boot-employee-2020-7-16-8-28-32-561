package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeServiceImpl.getEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee getSpecificEmployee(@RequestParam("id") int id){
        return employeeServiceImpl.getSpecificEmployee(id);
    }

    @PostMapping("/employees")
    public void addEmployees(@RequestBody Employee employee){
        employeeServiceImpl.addEmployees(employee);
    }
}
