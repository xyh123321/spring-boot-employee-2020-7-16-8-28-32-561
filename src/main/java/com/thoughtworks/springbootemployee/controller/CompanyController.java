package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    CompanyServiceImpl companyService;

    @GetMapping("/companies/{id}")
    private Company getCompany(@PathVariable("id") int id) {
        return companyService.getCompany(id);
    }

    @GetMapping("/companies/{id}/employees")
    private List<Employee> getAllEmployeesOfCompany(@PathVariable("id") int id) {
        return companyService.getAllEmployeesOfCompany(id);
    }

    @GetMapping("/companies")
    private List<Company> queryCompanies(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                         @RequestParam(value = "pageSize", required = false, defaultValue = "0") int pageSize) {
        if (page != 0 && pageSize != 0) {
            return companyService.pagingQueryCompanies(page, pageSize);
        }
        return companyService.getAllCompanies();
    }

    @PostMapping("/companies")
    private void addCompany(@RequestBody() Company company) {
        companyService.addCompany(company);
    }

    @DeleteMapping("/companies/{id}")
    private void deleteAllEmployeesOfCompany(@PathVariable("id") int id) {
        companyService.deleteAllEmployeesOfCompany(id);
    }

    @PutMapping("/companies/{id}")
    public void updateCompany(@PathVariable("id") int id, @RequestBody Company company) {
        companyService.updateCompany(id, company);
    }
}
