package com.thoughtworks.springbootemployee.entity;

public class Company {
    private String companyName;
    private int id;

    public Company(int id, String companyName) {
        this.companyName = companyName;
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
