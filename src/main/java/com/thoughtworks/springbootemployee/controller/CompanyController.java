package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    CompanyServiceImpl companyService;

    @GetMapping("/companies/{id}")
    private Company getCompany(@PathVariable("id") int id){
        return companyService.getCompany(id);
    }

    @GetMapping("/companies/{id}/employees")
    private List<Employee> getAllEmployeesOfCompany(@PathVariable("id") int id){
        return companyService.getAllEmployeesOfCompany(id);
    }

    @GetMapping("/companies")
    private List<Company> queryCompanies(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                               @RequestParam(value = "pageSize", required = false, defaultValue = "0") int pageSize){
        if(page != 0 && pageSize !=0){
            return companyService.pagingQueryCompanies(page, pageSize);
        }
        return companyService.getAllCompanies();
    }

}
