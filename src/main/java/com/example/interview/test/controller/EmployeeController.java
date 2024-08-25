package com.example.interview.test.controller;

import com.example.interview.test.dto.EmployeeDto;
import com.example.interview.test.service.DepartmentServiceImpl;
import com.example.interview.test.service.EmployeeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    private EmployeeServiceImpl employeeServiceImpl;
    private DepartmentServiceImpl departmentServiceImpl;

    EmployeeController(EmployeeServiceImpl employeeServiceImpl, DepartmentServiceImpl departmentServiceImpl){
        this.employeeServiceImpl = employeeServiceImpl;
        this.departmentServiceImpl = departmentServiceImpl;
    }


    //Add employee in Department
    @PostMapping("/employee/department/{deptId}")
    public void addNewEmployeeToDepartment(@PathVariable final String deptId, @Valid  @RequestBody  final EmployeeDto employeeDto) throws Exception {
          employeeServiceImpl.addEmployeeToDepartment(deptId, employeeDto);
    }

    //Get method to get all employee
    @GetMapping("/employees")
    public List<EmployeeDto> getEmployees (){
        return employeeServiceImpl.getEmployees();
    }

    //get employee in a department
    @GetMapping("/employee/department/{deptId}")
    public List<EmployeeDto> getEmployeeInDepartment (@PathVariable final String deptId){
        return employeeServiceImpl.getEmployeeInDepartment(deptId);
    }

    // Delete an employee by ID
    @DeleteMapping("/employee/{empId}")
    public void deleteEmployee(@PathVariable final String empId) {
        employeeServiceImpl.deleteEmployee(empId);
    }

}
