package com.example.interview.test.service;

import com.example.interview.test.dto.DepartmentDto;
import com.example.interview.test.entity.DepartmentEntity;
import com.example.interview.test.entity.EmployeeEntity;
import com.example.interview.test.dto.EmployeeDto;
import com.example.interview.test.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl {

    private EmployeeRepository employeeRepository;
    private DepartmentServiceImpl departmentServiceImpl;
    private ModelMapper modelMapper;

    EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentServiceImpl departmentServiceImpl, ModelMapper modelMapper){
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.departmentServiceImpl = departmentServiceImpl;
    }

    @Transactional
    public void addEmployeeToDepartment(String deptId, EmployeeDto employeeDto) {

        EmployeeEntity employeeEntity = convertEmployeeDtoToEntity(employeeDto);
        // TODO : Rename the addEmployeeToDepartment appropriately???
        DepartmentDto departmentDto = departmentServiceImpl.addEmployeeToDepartment(employeeDto,deptId);
        DepartmentEntity departmentEntity = convertDepartmentDtoToEntity(departmentDto);
        employeeEntity.setDepartment(departmentEntity);
        employeeRepository.save(employeeEntity);
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

    private DepartmentEntity convertDepartmentDtoToEntity(DepartmentDto departmentDto) {
        DepartmentEntity department = new DepartmentEntity();
        department.setId(departmentDto.getId());
        department.setName(departmentDto.getName());
        department.setLocation(departmentDto.getLocation());
        return department;
    }

    @Transactional
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
        employeeDto.setDepartment(employeeEntity.getDepartment().getName());
        return employeeDto;
    }

    @Transactional
    public List<EmployeeDto> getEmployeeInDepartment(String deptId) {
        DepartmentDto departmentDtoList =  departmentServiceImpl.getDepartmentById(deptId);
        List<EmployeeDto> employeeList = departmentDtoList.getEmployees();
        return modelMapper.map(employeeList, new TypeToken<List<EmployeeDto>>() {}.getType());
    }

    @Transactional
    public void deleteEmployee(String empId) {
        employeeRepository.deleteById(empId);
    }

}
