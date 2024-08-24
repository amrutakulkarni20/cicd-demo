package com.example.interview.test.repository;

import com.example.interview.test.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {

    //List<EmployeeEntity> findByDepartmentId(String departmentId);
}