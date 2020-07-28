package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private List<Employee> employees = new ArrayList<>();
    private Map<Company, List<Employee>> relationship= new HashMap<>();
    private Company company;

    public List<Employee> getEmployees() {
        return employees;
    }

    public Employee getSpecificEmployee(int id) {
        return employees.get(id);
    }

    public void addEmployees(Employee employee) {
        if(employees.stream()
                .filter(newEmployee -> employee.getId() == newEmployee.getId())
                .findFirst()
                .orElse(null) == null){
            employees.add(employee);
        }
//        if(!(relationship.containsValue(employees))){
//            relationship.get(company).add(employee);
//        }
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

    public List<Employee> pagingQueryEmployees(int page, int pageSize) {
        return employees.stream().skip((page-1)*pageSize).limit(pageSize).collect(Collectors.toList());
    }
}
