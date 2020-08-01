package com.thoughtworks.springbootemployee.service.impl;

import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    private final EmployeeRepository employeeRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<CompanyResponse> getAllCompanies() {
        List<Company> companies= companyRepository.findAll();
        List<CompanyResponse> companyResponses = new ArrayList<>();
        for (Company company: companies) {
            CompanyResponse companyResponse = new CompanyResponse();
            companyResponse.setName(company.getName());
            companyResponse.setStaffNum(company.getEmployeeList().size());
            companyResponses.add(companyResponse);
        }
        return companyResponses;
    }

    @Override
    public CompanyResponse getCompany(int id) {
        Company company = companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setName(company.getName());
        companyResponse.setStaffNum(company.getEmployeeList().size());
        return companyResponse;
    }

    @Override
    public List<Employee> getAllEmployeesOfCompany(int id) {
        Company company = companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
        return company.getEmployeeList();
    }

    @Override
    public List<CompanyResponse> pagingQueryCompanies(Pageable pageable) {
        List<Company> companies= companyRepository.findAll(pageable).getContent();
        List<CompanyResponse> companyResponses = new ArrayList<>();
        for (Company company: companies) {
            CompanyResponse companyResponse = new CompanyResponse();
            companyResponse.setName(company.getName());
            companyResponse.setStaffNum(company.getEmployeeList().size());
            companyResponses.add(companyResponse);
        }
        return companyResponses;
    }

    @Override
    public boolean addCompany(Company company) {
        companyRepository.save(company);
        return true;
    }

    @Override
    public void deleteTheCompanyAllInfo(int id) {
        Company company = companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
        employeeRepository.findAll().stream()
                .filter(employee -> employee.getCompany().getCompanyID()==company.getCompanyID())
                .forEach(employee -> employee.setCompany(null));
        companyRepository.deleteById(id);
    }

    @Override
    public void updateCompany(Company company) {
        companyRepository.save(company);
    }


}
