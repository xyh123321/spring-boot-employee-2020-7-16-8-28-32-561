package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.entity.Relationship;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    List<Company> companyList = new ArrayList<>();
    Relationship relationship = new Relationship();

    @Override
    public List<Company> getAllCompanies() {
        return companyList;
    }

    @Override
    public Company getCompany(int id) {
        return companyList.stream().filter(company -> company.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Employee> getAllEmployeesOfCompany(int id) {
        Company company = getCompany(id);
        return relationship.getAllEmployees(company);
    }
}
