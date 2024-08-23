package lk.benjarong.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lk.benjarong.ems.entity.DepartmentEntity;
import lk.benjarong.ems.entity.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    @Query("SELECT p FROM EmployeeEntity p WHERE p.departmentEntity = :departmentEntity")
    List<EmployeeEntity> findEmployeesByDepartment(@Param("departmentEntity") DepartmentEntity departmentEntity);
}
