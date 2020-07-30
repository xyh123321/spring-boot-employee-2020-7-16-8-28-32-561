package com.thoughtworks.springbootemployee.dto;

public class EmployeeResponse {
    private String name;
    private String gender;
    private String companyName;

    public EmployeeResponse() {
    }

    public EmployeeResponse(String name, String gender, String companyName) {
        this.name = name;
        this.gender = gender;
        this.companyName = companyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
