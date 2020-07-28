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

    @GetMapping("/employees/{id}")
    public Employee getSpecificEmployee(@RequestParam("id") int id){
        return employeeServiceImpl.getSpecificEmployee(id);
    }

    @PostMapping("/employees")
    public void addEmployees(@RequestBody Employee employee){
        employeeServiceImpl.addEmployees(employee);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployees(@PathVariable("id") int id){
        employeeServiceImpl.deleteEmployees(id);
    }

    @RequestMapping("/employees")
    public List<Employee> getMaleEmployees(@RequestParam(value = "gender", required = false, defaultValue = "") String gender){
        if("".equals(gender)){
            return employeeServiceImpl.getEmployees();
        }
        return employeeServiceImpl.getMaleEmployees(gender);
    }

    @PutMapping("/employees/{id}")
    public void updateEmployees(@PathVariable("id") int id, @RequestBody Employee employee){
        employeeServiceImpl.updateEmployees(id, employee);
    }
}
