package com.example.interview.test.controller;

import com.example.interview.test.InterviewTestApplication;
import com.example.interview.test.dto.DepartmentDto;
import com.example.interview.test.dto.EmployeeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
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

    @BeforeEach
    public void setup() {
        createDepartment();
    }

    private void createDepartment() {

        ResponseEntity<DepartmentDto> departmentDto = createNewDepartment();
        ResponseEntity<String> employeeResponse = testRestTemplate.exchange(createURLForRequest("/department", host, testPort),
                HttpMethod.POST, departmentDto, String.class);
    }

    private ResponseEntity<DepartmentDto> createNewDepartment() {

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId("1");
        departmentDto.setName("HR Department");
        departmentDto.setLocation("Building A");
        return ResponseEntity.ok(departmentDto);
    }

    @Test
    void createsEmployeeInDepartmentAndVerifiesStatusCode(){
        ResponseEntity<EmployeeDto> employeeDto = createEmployee();
        ResponseEntity<String> employeeResponse = testRestTemplate.exchange(createURLForRequest("/employee/department/1", host, testPort),
                HttpMethod.POST, employeeDto, String.class);

        assertNotNull(employeeResponse);
        assertEquals(HttpStatus.OK, employeeResponse.getStatusCode());
    }

    private ResponseEntity<EmployeeDto> createEmployee() {
        EmployeeDto employee = new EmployeeDto();
        employee.setId("1");
        employee.setName("Amruta Kulkarni");
        employee.setEmail("amruta@gmail.com");
        employee.setPosition("Senior Manager");
        employee.setSalary(50000.00);
        return ResponseEntity.ok(employee);
    }

    private String createURLForRequest(String uri, String host, String testPort) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://")
                .append(host)
                .append(":")
                .append(testPort)
                .append(uri);
        return stringBuilder.toString();
    }
}
