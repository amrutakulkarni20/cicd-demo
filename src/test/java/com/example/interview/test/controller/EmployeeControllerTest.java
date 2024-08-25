package com.example.interview.test.controller;

import com.example.interview.test.InterviewTestApplication;
import com.example.interview.test.dto.EmployeeDto;
import com.example.interview.test.entity.EmployeeEntity;
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

    @Test
    void createsEmployeeInDepartmentAndVerifiesStatusCode(){
       // ResponseEntity createEmployeeResponse = createEmployeeInDepartment();
//        assertNotNull(createEmployeeResponse);
//        assertTrue(createEmployeeResponse.getStatusCode() == HttpStatus.OK);
//        assertTrue(!createEmployeeResponse.getBody().getId().isEmpty());
    }

//    private ResponseEntity createEmployeeInDepartment() {
//
//        final Account accountRequest = TestDataUtils.getNewBankAccountRequest();
//        final String url = TestDataUtils.createURLWithPort("/account-management/account",host, port);
//
//        HttpEntity<Account> newBankAccountRequest = new HttpEntity<Account>(accountRequest, null);
//        ResponseEntity<Object> response = testRestTemplate.exchange(url, HttpMethod.POST, newBankAccountRequest, Object.class);
//
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertNotSame(0, response.getBody());
//    }



    /*private ResponseEntity<EmployeeDto> createEmployeeInDepartment() {
        String deptId = "123";
        ResponseEntity<EmployeeEntity> employee = createEmployee();
         //ResponseEntity<List<EmployeeDto>> employeeDto = testRestTemplate.exchange(createURLForRequest("/department/"+deptId+"/employee", host, testPort),
         //               HttpMethod.POST, employee, new ParameterizedTypeReference<>() {});

        ResponseEntity<EmployeeDto> responseEntity = testRestTemplate.exchange(
                createURLForRequest("/department/" + deptId + "/employee", host, testPort),
                HttpMethod.POST,
                employee,
                new ParameterizedTypeReference<EmployeeDto>() {}
        );

        return responseEntity;

    }


     */

    private EmployeeDto createEmployee() {
        EmployeeDto employee = new EmployeeDto();
        employee.setId("1");
        employee.setName("Amruta Kulkarni");
        employee.setEmail("amruta@gmail.com");
        employee.setPosition("Senior Manager");
        employee.setSalary(50000.00);
        return employee;
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
