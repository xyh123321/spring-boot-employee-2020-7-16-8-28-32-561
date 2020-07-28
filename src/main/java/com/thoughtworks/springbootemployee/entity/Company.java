package com.thoughtworks.springbootemployee.entity;

import java.util.List;

public class Company {
    private String companyName;
    private List<Employee> employee;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }
}
