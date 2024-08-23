package lk.benjarong.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lk.benjarong.ems.dto.EmployeeDto;
import lk.benjarong.ems.entity.EmployeeEntity;
import lk.benjarong.ems.service.EmployeeService;

@RestController
@CrossOrigin(origins = "*")
public class EmployeeController {  

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employees")
    public ResponseEntity<EmployeeEntity> addEmployee(@RequestBody EmployeeDto employeeDto) {
        try {
            return ResponseEntity.status(200).body(employeeService.addEmployee(employeeDto));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeEntity>> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeService.getAllEmployees();
        if (employeeEntities != null) {
            return ResponseEntity.status(200).body(employeeEntities);
        } else {
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable Long id) {
        EmployeeEntity employeeEntity = employeeService.getEmployeeById(id);
        if (employeeEntity != null) {
            return ResponseEntity.status(HttpStatus.OK).body(employeeEntity);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/employees/{id}")
    public EmployeeEntity updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        return employeeService.updateEmployee(id, employeeDto);
    }

    @DeleteMapping("/employees/{id}")
    public EmployeeEntity deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }

    @GetMapping("/departments/{id}/employees")
    public ResponseEntity<List<EmployeeEntity>> getEmployeesByDepartments(@PathVariable Long id) {
        return ResponseEntity.ok().body(employeeService.getEmployeesByDepartment(id));
    }

}
