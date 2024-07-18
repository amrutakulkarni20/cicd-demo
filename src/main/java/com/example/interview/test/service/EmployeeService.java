package com.example.interview.test.service;

import com.example.interview.test.entity.EmployeeEntity;
import com.example.interview.test.model.EmployeeModel;
import com.example.interview.test.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    private ModelMapper modelMapper;

    EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper){
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public void saveEmployee(EmployeeModel employeeModel) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeModel,EmployeeEntity.class);
        employeeRepository.save(employeeEntity);
    }

    public List<EmployeeModel> getEmployee() {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
        Type type = new TypeToken<List<EmployeeModel>>(){}.getType();
        List<EmployeeModel> employeeModelList = modelMapper.map(employeeEntityList,type);
        return employeeModelList;
    }
}
