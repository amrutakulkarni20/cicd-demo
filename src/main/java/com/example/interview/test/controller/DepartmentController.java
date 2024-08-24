package com.example.interview.test.controller;

import com.example.interview.test.dto.DepartmentDto;
import com.example.interview.test.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    //Add department
    @PostMapping("/department")
    public void save(@RequestBody DepartmentDto departmentDto){
        departmentService.saveDepartment(departmentDto);
    }

    //Get all departments
    @GetMapping("/departments")
    public List<DepartmentDto> get (){
        return departmentService.getDepartments();
    }
}
