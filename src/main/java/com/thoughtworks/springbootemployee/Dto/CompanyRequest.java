package com.thoughtworks.springbootemployee.Dto;

import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Validated
public class CompanyRequest {
    int id;
    @NotBlank
    @Size(min = 1, max = 20)
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CompanyRequest(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CompanyRequest() {

    }
}
