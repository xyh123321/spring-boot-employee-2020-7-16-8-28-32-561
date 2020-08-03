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
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Rollback
public class EmployeeIntegrationTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    MockMvc mockMvc;

//    @AfterEach
//    void teardown(){
//        employeeRepository.deleteAll();
//        companyRepository.deleteAll();
//    }

    @Test
    void should_return_all_employees_when_get_all_employees() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employees").param("unpaged", "true"))
                .andExpect(status().isOk());
    }

    @Test
    void should_return_employee_when_add_employee() throws Exception {
        Company company = new Company("oocl");
        companyRepository.save(company);
        String body = " {\n" +
                "                \"name\": \"chengcheng2222\",\n" +
                "                \"gender\": \"male\",\n" +
                "                \"age\": 54,\n" +
                "                \"companyId\":1\n" +
                "            }";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk());
        List<Employee> employees = employeeRepository.findAll();
        assertEquals(1,employees.size());
    }

    @Test
    void should_return_404_when_add_employee() throws Exception {
        employeeRepository.deleteAll();
        companyRepository.deleteAll();
        String body = " {\n" +
                "                \"name\": \"chengcheng2222\",\n" +
                "                \"gender\": \"male\",\n" +
                "                \"age\": 54,\n" +
                "                \"companyId\":1\n" +
                "            }";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isNotFound());
    }

    @Test
    void should_return_ok_when_delete_employee_given_a_employee_in_employee_repo() throws Exception {
        Employee employee = new Employee();
        employee.setName("test");
        employee.setGender("male");
        employee.setAge(12);
        Employee saveEmployee = employeeRepository.save(employee);
        int id = saveEmployee.getId();
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/employees/"+id)).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders
                .get("/employees/"+id)).andExpect(status().isNotFound());
    }

    @Test
    void should_return_404_when_delete_employee_given_empty_employee_repo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/employees/1")).andExpect(status().isNotFound());
    }

    @Test
    void should_return_new_column_when_update_employee_given_a_employee_in_employee_repo() throws Exception {
        Employee employee = new Employee();
        employee.setName("test");
        employee.setGender("male");
        employee.setAge(12);
        Employee saveEmployee = employeeRepository.save(employee);
        int id = saveEmployee.getId();
        String body = "{\n" +
                "    \"name\":\"test\",\n" +
                "    \"gender\":\"female\",\n" +
                "    \"age\":10\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders
                .put("/employees/"+id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));
        Optional<Employee> updateEmployee = employeeRepository.findById(id);
        assertEquals(10,updateEmployee.get().getAge());
    }

    @Test
    void should_return_false_when_get_male_employee_given_a_female_employee_in_employee_repo() throws Exception {
        Employee employee = new Employee();
        employee.setName("test");
        employee.setGender("female");
        Employee saveEmployee = employeeRepository.save(employee);
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                .get("/employees").param("gender", "male")).andReturn().getResponse();
        assertFalse(response.getContentAsString().contains("test"));
    }

}
