package com.thoughtworks.springbootemployee.service.impl;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    List<Company> companyList = new ArrayList<>();

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Company> getAllCompanies() {
        return companyList;
    }

    @Override
    public Company getCompany(int id) {
       // return companyList.stream().filter(company -> company.getId() == id).findFirst().orElse(null);
        return companyRepository.getCompany(id);
    }

    @Override
    public List<Employee> getAllEmployeesOfCompany(int id) {
        Company company = getCompany(id);
        return company.getEmployeeList();
    }

    @Override
    public List<Company> pagingQueryCompanies(int page, int pageSize) {
        return companyList.stream().skip((page - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
    }

    @Override
    public boolean addCompany(Company company) {

        return false;
    }

    @Override
    public void deleteAllEmployeesOfCompany(int id) {
        companyList.stream().filter(e -> e.getId() == id).findFirst().ifPresent(company -> company.getEmployeeList().clear());
    }

    @Override
    public void updateCompany(int id, Company company) {
        Company originCompany = companyList.stream().filter(dataBaseCompany -> company.getId() == dataBaseCompany.getId()).findFirst().orElse(null);
        if (originCompany != null) {
            companyList.remove(originCompany);
            companyList.add(company);
        }

    }


}
