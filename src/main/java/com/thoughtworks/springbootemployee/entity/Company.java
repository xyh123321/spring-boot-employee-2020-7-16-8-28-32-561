package com.thoughtworks.springbootemployee.entity;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private String companyName;
    private int id;

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
