package com.example.interview.test.controller;

import com.example.interview.test.dto.DepartmentDto;
import com.example.interview.test.service.DepartmentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class DepartmentController {

    private DepartmentServiceImpl departmentServiceImpl;

    public DepartmentController(DepartmentServiceImpl departmentServiceImpl){
        this.departmentServiceImpl = departmentServiceImpl;
    }

    //Add department
    @PostMapping("/department")
    public void save(@Valid @RequestBody  DepartmentDto departmentDto){
        departmentServiceImpl.saveDepartment(departmentDto);
    }

    //Get all departments
    @GetMapping("/departments")
    public List<DepartmentDto> get (){
        return departmentServiceImpl.getDepartments();
    }
}
