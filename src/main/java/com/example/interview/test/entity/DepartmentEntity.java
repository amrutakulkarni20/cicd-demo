package com.example.interview.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "department")
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "location", length = 255)
    private String location;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<EmployeeEntity> employees;

    public List<EmployeeEntity> getEmployees() {
        return employees;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
