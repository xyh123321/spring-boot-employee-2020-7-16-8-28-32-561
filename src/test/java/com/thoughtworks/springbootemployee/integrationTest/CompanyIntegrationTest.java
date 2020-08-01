package com.thoughtworks.springbootemployee.integrationTest;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Rollback
public class CompanyIntegrationTest {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    void should_return_ok_when_get_all_companies() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/companies").param("unpaged", "true"))
                .andExpect(status().isOk());
    }

    @Test
    void should_return_1_company_when_add_a_company() throws Exception {
        companyRepository.deleteAll();
        String body = "{\n" +
                "    \"name\": \"tw2242\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk());
        List<Company> companies = companyRepository.findAll();
        assertEquals(1,companies.size());
    }

    @Test
    void should_return_ok_when_delete_companies_given_a_company_in_company_repo() throws Exception {
        Company company = new Company();
        company.setName("oo");
        Company saveCompany = companyRepository.save(company);
        int id = saveCompany.getCompanyID();
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/companies/"+id)).andExpect(status().isOk());
    }

    @Test
    void should_return_404_when_delete_companies_given_empty_company_repo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/companies/10")).andExpect(status().isNotFound());
    }

    @Test
    void should_return_new_column_when_update_company_given_a_company_in_company_repo() throws Exception {
        Company company = new Company();
        company.setName("oo");
        Company saveCompany = companyRepository.save(company);
        int id = saveCompany.getCompanyID();
        String body = "{\n" +
                "    \"companyID\":"+id+",\n" +
                "    \"name\": \"cl\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders
                .put("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));
        Optional<Company> updateCompany = companyRepository.findById(id);
        assertEquals("cl",updateCompany.get().getName());
    }
}
