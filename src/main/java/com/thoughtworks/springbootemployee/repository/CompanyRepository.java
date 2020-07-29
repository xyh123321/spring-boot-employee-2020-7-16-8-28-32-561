package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.entity.Company;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository {
    Company getCompany(int id);

    boolean addCompany(Company company);

    List<Company> getAllCompanies();
}
