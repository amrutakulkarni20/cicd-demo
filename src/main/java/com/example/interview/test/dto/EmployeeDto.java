package com.example.interview.test.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.io.Serializable;

@Data
public class EmployeeDto implements Serializable {

    @NotBlank
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String position;

    @DecimalMin("0.1")
    private double salary;

    @Min(1)
    private long departmentId;
}
