package com.example.interview.test.service;

import com.example.interview.test.dto.DepartmentDto;
import com.example.interview.test.entity.DepartmentEntity;
import com.example.interview.test.entity.EmployeeEntity;
import com.example.interview.test.dto.EmployeeDto;
import com.example.interview.test.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentService departmentService;
    private ModelMapper modelMapper;

    EmployeeService(EmployeeRepository employeeRepository, DepartmentService departmentService, ModelMapper modelMapper){
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.departmentService = departmentService;
    }

    public void addEmployeeToDepartment(String deptId, EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = convertEmployeeDtoToEntity(employeeDto);
        DepartmentDto departmentDto = departmentService.addEmployeeToDepartment(employeeDto,deptId);
        DepartmentEntity departmentEntity = convertDepartmentDtoToEntity(departmentDto);
        employeeEntity.setDepartment(departmentEntity);
        employeeRepository.save(employeeEntity);
    }

    private DepartmentEntity convertDepartmentDtoToEntity(DepartmentDto departmentDto) {
        DepartmentEntity department = new DepartmentEntity();
        department.setId(departmentDto.getId());
        department.setName(departmentDto.getName());
        department.setLocation(departmentDto.getLocation());
        return department;
    }

    private EmployeeEntity convertEmployeeDtoToEntity(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(employeeDto.getId());
        employeeEntity.setName(employeeDto.getName());
        employeeEntity.setEmail(employeeDto.getEmail());
        employeeEntity.setPosition(employeeDto.getPosition());
        employeeEntity.setSalary(employeeDto.getSalary());
        return employeeEntity;
    }

    public List<EmployeeDto> getEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::convertEmployeeEntityToDTO)
                .toList();
    }

    private EmployeeDto convertEmployeeEntityToDTO(EmployeeEntity employeeEntity) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employeeEntity.getId());
        employeeDto.setName(employeeEntity.getName());
        employeeDto.setEmail(employeeEntity.getEmail());
        employeeDto.setPosition(employeeEntity.getPosition());
        employeeDto.setSalary(employeeEntity.getSalary());
        employeeDto.setDepartmentId(Long.parseLong(employeeEntity.getDepartment().getId()));
        return employeeDto;
    }

    public List<EmployeeDto> getEmployeeInDepartment(String deptId) {
        DepartmentDto departmentDtoList =  departmentService.getDepartmentById(deptId);
        List<EmployeeDto> employeeList = departmentDtoList.getEmployees();
        return modelMapper.map(employeeList, new TypeToken<List<EmployeeDto>>() {}.getType());
    }

    public void deleteEmployee(String empId) {
        employeeRepository.deleteById(empId);
    }


}
