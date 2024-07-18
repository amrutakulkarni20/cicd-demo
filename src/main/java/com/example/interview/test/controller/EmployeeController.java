package com.example.interview.test.controller;

import com.example.interview.test.model.EmployeeModel;
import com.example.interview.test.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    private EmployeeService employeeService;

    EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping("/employee")
    public void saveEmployee (@RequestBody EmployeeModel employeeModel){
        employeeService.saveEmployee(employeeModel);
    }

    @GetMapping("/employees")
    public List<EmployeeModel> getEmployee (){
        return employeeService.getEmployee();
    }
}
