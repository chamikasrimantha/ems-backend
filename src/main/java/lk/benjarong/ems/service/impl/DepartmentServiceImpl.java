package lk.benjarong.ems.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lk.benjarong.ems.entity.DepartmentEntity;
import lk.benjarong.ems.repository.DepartmentRepository;
import lk.benjarong.ems.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentEntity createDepartment(DepartmentEntity departmentEntity) {
        return departmentRepository.save(departmentEntity);
    }

    @Override
    public DepartmentEntity deleteDepartment(Long id) {
        DepartmentEntity departmentEntity = departmentRepository.findById(id).orElse(null);
        if (departmentEntity!=null) {
            departmentRepository.delete(departmentEntity);
            return departmentEntity;
        } else {
            return null;
        }
    }

    @Override
    public List<DepartmentEntity> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public DepartmentEntity getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public DepartmentEntity updateDepartment(Long id, DepartmentEntity departmentEntity) {
        DepartmentEntity existingDepartment = departmentRepository.findById(id).orElse(null);
        if (existingDepartment!=null) {
            existingDepartment.setName(departmentEntity.getName());
            existingDepartment.setDescription(departmentEntity.getDescription());
            return departmentRepository.save(existingDepartment);
        } else {
            return null;
        }
    }

}
