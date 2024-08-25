package com.example.interview.test.service;

import com.example.interview.test.entity.DepartmentEntity;
import com.example.interview.test.entity.EmployeeEntity;
import com.example.interview.test.dto.DepartmentDto;
import com.example.interview.test.dto.EmployeeDto;
import com.example.interview.test.exception.InvalidInputDataException;
import com.example.interview.test.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl {

    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;

    DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper modelMapper){
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public void saveDepartment(DepartmentDto departmentDto) {
        DepartmentEntity departmentEntity = convertDepartmentDtoToEntity(departmentDto);
        departmentRepository.save(departmentEntity);
    }

    private DepartmentEntity convertDepartmentDtoToEntity(DepartmentDto departmentDto) {
        DepartmentEntity department = new DepartmentEntity();
        department.setId(departmentDto.getId());
        department.setName(departmentDto.getName());
        department.setLocation(departmentDto.getLocation());
        return department;
    }

    @Transactional
    public List<DepartmentDto> getDepartments() {
        Optional<List<DepartmentEntity>> departmentEntityList = Optional.of(departmentRepository.findAll());
        List<DepartmentDto> departmentDtoList = new ArrayList<>();
        if(departmentEntityList.isPresent() && departmentEntityList.get().size()>0){
            departmentDtoList = convertDepartmentEntityListToDto(departmentEntityList.get());
            TypeToken<List<DepartmentDto>> typeToken = new TypeToken<>() {};
            return modelMapper.map(departmentEntityList.get(), typeToken.getType());
        }
        return departmentDtoList;
    }

    private List<DepartmentDto> convertDepartmentEntityListToDto(List<DepartmentEntity> departmentEntities) {
        List<DepartmentDto> departmentDtoList = new ArrayList<>();

        for (DepartmentEntity departmentEntity : departmentEntities){
            DepartmentDto departmentDto = new DepartmentDto();
            departmentDto.setId(departmentEntity.getId());
            departmentDto.setName(departmentEntity.getName());
            departmentDto.setLocation(departmentEntity.getLocation());
            //departmentDto.setEmployees();
            departmentDtoList.add(departmentDto);
        }
        return departmentDtoList;
    }

    @Transactional
    public DepartmentDto getDepartmentById(String deptId) {
        DepartmentEntity departmentEntity = departmentRepository.findById(deptId)
                .orElseThrow(()-> new InvalidInputDataException("Department with ID " + deptId + " not found"));
        DepartmentDto departmentDto = convertDepartmentEntityToDto(departmentEntity);
        return departmentDto;
       // return modelMapper.map(departmentEntity, DepartmentDto.class);
    }

    @Transactional
    public DepartmentDto addEmployeeToDepartment(EmployeeDto employeeDto, String deptId) {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(deptId);
        if(departmentEntity.isPresent()){
            return convertDepartmentEntityToDto(departmentEntity.get());
        }else {
            throw new InvalidInputDataException("Department with ID " + deptId + " not found");
        }

    }

    private DepartmentDto convertDepartmentEntityToDto(DepartmentEntity departmentEntity) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(departmentEntity.getId());
        departmentDto.setName(departmentEntity.getName());
        departmentDto.setLocation(departmentEntity.getLocation());
        List<EmployeeDto> employeesDto = convertEmployeeEntityToDto(departmentEntity.getEmployees());
        departmentDto.setEmployees(employeesDto);
        return departmentDto;
    }

    private List<EmployeeDto> convertEmployeeEntityToDto(List<EmployeeEntity> employees) {

        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        for (EmployeeEntity employee : employees){
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setId(employee.getId());
            employeeDto.setName(employee.getName());
            employeeDto.setEmail(employee.getEmail());
            employeeDto.setSalary(employee.getSalary());
            employeeDto.setPosition(employee.getPosition());
            employeeDto.setDepartment(employee.getDepartment().getName());
            employeeDtoList.add(employeeDto);
        }
        return employeeDtoList;
    }

    private List<EmployeeDto> setEmployeesToEmployeeDto(List<EmployeeEntity> employees) {
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        EmployeeDto employeeDto = new EmployeeDto();
        for (EmployeeEntity employeeEntity : employees){
            employeeDto.setDepartment(employeeEntity.getDepartment().getName());
        }
        employeeDtoList.add(employeeDto);
        return employeeDtoList;
    }

}
