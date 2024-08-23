package lk.benjarong.ems.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lk.benjarong.ems.dto.SalaryDto;
import lk.benjarong.ems.entity.SalaryEntity;

@Service
public interface SalaryService {
    SalaryEntity saveSalary(SalaryDto salaryDto);
    List<SalaryEntity> getAllSalaries();
    SalaryEntity getSalaryById(Long id);
    SalaryEntity updateSalary(Long id, SalaryDto salaryDto);
    SalaryEntity deleteSalary(Long id);
    List<SalaryEntity> getSalariesByEmployee(Long id);
    List<SalaryEntity> getSalariesByMonth(Long id);
}
