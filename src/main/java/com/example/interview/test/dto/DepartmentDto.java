package com.example.interview.test.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class DepartmentDto implements Serializable {

    @NotBlank
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String location;

    @NotNull
    private List<EmployeeDto> employees;

}
