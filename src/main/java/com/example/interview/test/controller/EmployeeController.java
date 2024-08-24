package com.example.interview.test.controller;

import com.example.interview.test.dto.EmployeeDto;
import com.example.interview.test.service.DepartmentService;
import com.example.interview.test.service.EmployeeService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class EmployeeController {

    private EmployeeService employeeService;
    private DepartmentService departmentService;

    EmployeeController(EmployeeService employeeService, DepartmentService departmentService){
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }


    //Add employee in Department
    @PostMapping("/employee/department/{deptId}")
    public void addNewEmployeeToDepartment(@PathVariable final String deptId, @RequestBody final EmployeeDto employeeDto) throws Exception {
          employeeService.addEmployeeToDepartment(deptId, employeeDto);
    }

    //Get method to get all employee
    @GetMapping("/employees")
    public List<EmployeeDto> getEmployees (){
        return employeeService.getEmployees();
    }

    //get employee in a department
    @GetMapping("/employee/department/{deptId}")
    public List<EmployeeDto> getEmployeeInDepartment (@PathVariable final String deptId){
        return employeeService.getEmployeeInDepartment(deptId);
    }

    // Delete an employee by ID
    @DeleteMapping("/employee/{empId}")
    public void deleteEmployee(@PathVariable final String empId) {
        employeeService.deleteEmployee(empId);
    }

}
