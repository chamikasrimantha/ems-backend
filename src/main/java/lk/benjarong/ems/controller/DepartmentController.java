package lk.benjarong.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import lk.benjarong.ems.entity.DepartmentEntity;
import lk.benjarong.ems.service.DepartmentService;

@RestController
@CrossOrigin(origins = "*")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/departments")
    public DepartmentEntity createDepartment(@RequestBody DepartmentEntity departmentEntity) {
        return departmentService.createDepartment(departmentEntity);
    }

    @GetMapping("/departments")
    public List<DepartmentEntity> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/departments/{id}")
    public DepartmentEntity getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    @PutMapping("/departments/{id}")
    public DepartmentEntity updateDepartment(@PathVariable Long id, @RequestBody DepartmentEntity departmentEntity) {
        return departmentService.updateDepartment(id, departmentEntity);
    }

    @DeleteMapping("/departments/{id}")
    public DepartmentEntity deleteDepartment(@PathVariable Long id) {
        return departmentService.deleteDepartment(id);
    }

}