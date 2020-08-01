package com.thoughtworks.springbootemployee.dto;

public class CompanyResponse {
    private String name;
    private int staffNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(int staffNum) {
        this.staffNum = staffNum;
    }
}
