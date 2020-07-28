package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.entity.Relationship;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public List<Company> pagingQueryCompanies(int page, int pageSize) {
        return companyList.stream().skip((page-1)*pageSize).limit(pageSize).collect(Collectors.toList());
    }

    @Override
    public void addCompany(Company company) {
        Map<Company, List<Employee>> map = new HashMap<>();
        if(companyList.stream().filter(dataBaseCompany -> company.getId() == dataBaseCompany.getId()).findFirst().orElse(null) == null){
            companyList.add(company);
            map.put(company,new ArrayList<>());
            relationship.setRelation(map);
        }
    }

    @Override
    public void deleteAllEmployeesOfCompany(int id) {
        companyList.stream().filter(e -> e.getId() == id).findFirst().ifPresent(company -> relationship.getAllEmployees(company).clear());
    }

    @Override
    public void updateCompany(int id, Company company) {
        Company originCompany = companyList.stream().filter(dataBaseCompany -> company.getId() == dataBaseCompany.getId()).findFirst().orElse(null);
        companyList.remove(originCompany);
        companyList.add(company);
    }


}
