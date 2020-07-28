package com.thoughtworks.springbootemployee.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Relationship {
    private Map<Company, List<Employee>> relation;

    public Relationship() {
        this.relation = new HashMap<>();
    }

    public Map<Company, List<Employee>> getRelation() {
        return relation;
    }

    public void setRelation(Map<Company, List<Employee>> relation) {
        this.relation = relation;
    }

    public List<Employee> getAllEmployees(Company company) {
        return relation.get(company);
    }
}
