package lk.benjarong.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lk.benjarong.ems.entity.EmployeeEntity;
import lk.benjarong.ems.entity.MonthEntity;
import lk.benjarong.ems.entity.SalaryEntity;

@Repository
public interface SalaryRepository extends JpaRepository<SalaryEntity, Long>{
    @Query("SELECT p FROM SalaryEntity p WHERE p.employeeEntity = :employeeEntity")
    List<SalaryEntity> findSalariesByEmployee(@Param("employeeEntity") EmployeeEntity employeeEntity);

    @Query("SELECT q FROM SalaryEntity q WHERE q.monthEntity = :monthEntity")
    List<SalaryEntity> findSalariesByMonth(@Param("monthEntity") MonthEntity monthEntity);
}
