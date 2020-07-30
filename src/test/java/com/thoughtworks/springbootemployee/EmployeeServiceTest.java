package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.Dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.Dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.impl.CompanyServiceImpl;
import com.thoughtworks.springbootemployee.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    CompanyRepository companyRepository;

    @InjectMocks
    EmployeeServiceImpl employeeService;

    @Test
    void should_return_employee_responses_when_get_employees_given_employees() {
        //given
        Employee employee = new Employee(1, "Eric", "male", 18, null);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        Mockito.when(employeeRepository.findAll()).thenReturn(employees);

        //when
        List<EmployeeResponse> employeeResponses = employeeService.getEmployees();

        //then
        assertEquals(1, employeeResponses.size());
    }

    @Test
    void should_return_new_employee_when_add_employee_given_new_employee() {
        //given
        Company company = new Company(1,"oocl");
        Employee employee = new Employee(1, "Eric", "male", 20, company);
        EmployeeRequest employeeRequest = new EmployeeRequest(1, "Eric", "male", 20, 1);
        Mockito.when(companyRepository.findById(1)).thenReturn(Optional.of(company));
        Mockito.when(employeeRepository.save(any())).thenReturn(employee);

        //when
        EmployeeResponse employeeResponse = employeeService.addEmployees2(employeeRequest);

        //then
        assertNotNull(employeeResponse);
    }
}
