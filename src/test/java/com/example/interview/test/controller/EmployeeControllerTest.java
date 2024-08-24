package com.example.interview.test.controller;

import com.example.interview.test.InterviewTestApplication;
import com.example.interview.test.dto.EmployeeDto;
import com.example.interview.test.entity.EmployeeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {InterviewTestApplication.class})
@TestPropertySource(locations = "classpath:test.properties")
public class EmployeeControllerTest {

    @Value("${server.port}")
    private String testPort;

    @Value("${application.test.host}")
    private String host;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void createEmployeeInDepartmentAndVerifiesStatusCode(){
        ResponseEntity<List<EmployeeDto>> createEmployeeResponse = createEmployeeInDepartment();
        assertNotNull(createEmployeeResponse);
        assertSame(HttpStatus.CREATED, createEmployeeResponse.getStatusCode());
    }

    private ResponseEntity<List<EmployeeDto>> createEmployeeInDepartment() {
        String deptId = "123";
        ResponseEntity<EmployeeEntity> employee = createEmployee();
         ResponseEntity<List<EmployeeDto>> employeeDto = testRestTemplate.exchange(createURLForGetRequest("/department/"+deptId+"/employee", host, testPort),
                        HttpMethod.POST, employee, new ParameterizedTypeReference<>() {});
                return employeeDto;
    }

    private ResponseEntity<EmployeeEntity> createEmployee() {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setId("1");
        employee.setName("Amruta Kulkarni");
        employee.setEmail("amruta@gmail.com");
        employee.setPosition("Senior Manager");
        employee.setSalary(50000.00);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    private String createURLForGetRequest(String uri, String host, String testPort) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://")
                .append(host)
                .append(":")
                .append(testPort)
                .append(uri);
        return stringBuilder.toString();
    }
}
