package lk.benjarong.ems.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lk.benjarong.ems.entity.DepartmentEntity;

@Service
public interface DepartmentService {
    DepartmentEntity createDepartment(DepartmentEntity departmentEntity);
    List<DepartmentEntity> getAllDepartments();
    DepartmentEntity getDepartmentById(Long id);
    DepartmentEntity updateDepartment(Long id, DepartmentEntity departmentEntity);
    DepartmentEntity deleteDepartment(Long id);
}
