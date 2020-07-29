package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.entity.Company;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository {
    Company getCompany(int id);

}
