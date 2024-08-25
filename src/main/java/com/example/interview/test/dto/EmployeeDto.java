package com.example.interview.test.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

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

    private String department;


    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "department=" + department +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public @NotBlank String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank String email) {
        this.email = email;
    }

    public @NotBlank String getId() {
        return id;
    }

    public void setId(@NotBlank String id) {
        this.id = id;
    }

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public @NotBlank String getPosition() {
        return position;
    }

    public void setPosition(@NotBlank String position) {
        this.position = position;
    }

    @DecimalMin("0.1")
    public double getSalary() {
        return salary;
    }

    public void setSalary(@DecimalMin("0.1") double salary) {
        this.salary = salary;
    }
}
