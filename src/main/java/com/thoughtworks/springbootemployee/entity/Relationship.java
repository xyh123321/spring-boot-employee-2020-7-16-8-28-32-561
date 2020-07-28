package com.thoughtworks.springbootemployee.entity;

import java.util.List;
import java.util.Map;

public class Relationship {

    private Map<Company, List<Employee>> relation;

    public Relationship(Map<Company, List<Employee>> relation) {
        this.relation = relation;
    }

    public Map<Company, List<Employee>> getRelation() {
        return relation;
    }

    public void setRelation(Map<Company, List<Employee>> relation) {
        this.relation = relation;
    }
}
