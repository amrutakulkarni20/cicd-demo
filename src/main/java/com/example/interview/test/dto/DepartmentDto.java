package com.example.interview.test.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class DepartmentDto implements Serializable {

    @NotBlank
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String location;

    @NotNull
    private List<EmployeeDto> employees;

    @Override
    public String toString() {
        return "DepartmentDto{" +
                "employees=" + employees +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public @NotNull List<EmployeeDto> getEmployees() {
        return employees;
    }

    public void setEmployees(@NotNull List<EmployeeDto> employees) {
        this.employees = employees;
    }

    public @NotBlank String getId() {
        return id;
    }

    public void setId(@NotBlank String id) {
        this.id = id;
    }

    public @NotBlank String getLocation() {
        return location;
    }

    public void setLocation(@NotBlank String location) {
        this.location = location;
    }

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }
}
