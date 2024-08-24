package com.example.interview.test.service;

import com.example.interview.test.entity.DepartmentEntity;
import com.example.interview.test.entity.EmployeeEntity;
import com.example.interview.test.dto.DepartmentDto;
import com.example.interview.test.dto.EmployeeDto;
import com.example.interview.test.exception.InvalidInputDataException;
import com.example.interview.test.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;

    DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper){
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

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

    public List<DepartmentDto> getDepartments() {
        Optional<List<DepartmentEntity>> departmentEntityList = Optional.of(departmentRepository.findAll());
        List<DepartmentDto> departmentDtoList = new ArrayList<>();
        if(departmentEntityList.isPresent() && departmentEntityList.get().size()>0){
            TypeToken<List<DepartmentDto>> typeToken = new TypeToken<>() {};
            return modelMapper.map(departmentEntityList.get(), typeToken.getType());
        }
        return departmentDtoList;
    }




    // TODO : Need to revisit implementation. Employee should be added in Employee resource and not in Department.
//    public void addEmployeeToDepartment(String deptId, EmployeeDto employeeDto) throws Exception {
//        departmentRepository.findById(deptId).map(department -> {
//            EmployeeEntity employeeEntity = modelMapper.map(employeeDto,EmployeeEntity.class);
//            department.getEmployees().add(employeeEntity);
//            return departmentRepository.save(department);
//        }).orElseThrow(() -> new InvalidInputDataException("Department with ID " + deptId + " not found"));
//    }

    public DepartmentDto getDepartmentById(String deptId) {
        DepartmentEntity departmentEntity = departmentRepository.findById(deptId)
                .orElseThrow(()-> new InvalidInputDataException("Department with ID " + deptId + " not found"));
        return modelMapper.map(departmentEntity, DepartmentDto.class);
    }

    // TODO : Method name is irrelevant???
    public DepartmentDto addEmployeeToDepartment(EmployeeDto employeeDto, String deptId) {
        DepartmentEntity departmentEntity = departmentRepository.findById(deptId)
                .orElseThrow(()-> new InvalidInputDataException("Department with ID " + deptId + " not found"));
        DepartmentDto department = modelMapper.map(departmentEntity, DepartmentDto.class);
        return department;
    }

}
