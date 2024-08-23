package lk.benjarong.ems.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lk.benjarong.ems.dto.EmployeeDto;
import lk.benjarong.ems.entity.EmployeeEntity;

@Service
public interface EmployeeService {
    EmployeeEntity addEmployee(EmployeeDto employeeDto);
    List<EmployeeEntity> getAllEmployees();
    EmployeeEntity getEmployeeById(Long id);
    EmployeeEntity updateEmployee(Long id, EmployeeDto employeeDto);
    EmployeeEntity deleteEmployee(Long id);
    List<EmployeeEntity> getEmployeesByDepartment(Long id);
}
