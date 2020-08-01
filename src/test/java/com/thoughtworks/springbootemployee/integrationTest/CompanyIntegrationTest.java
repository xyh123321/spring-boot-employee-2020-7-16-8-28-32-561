package com.thoughtworks.springbootemployee.integrationTest;

import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Rollback
public class CompanyIntegrationTest {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    MockMvc mockMvc;


}
