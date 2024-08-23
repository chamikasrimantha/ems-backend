package lk.benjarong.ems.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lk.benjarong.ems.dto.SalaryDto;
import lk.benjarong.ems.entity.EmployeeEntity;
import lk.benjarong.ems.entity.MonthEntity;
import lk.benjarong.ems.entity.SalaryEntity;
import lk.benjarong.ems.repository.EmployeeRepository;
import lk.benjarong.ems.repository.MonthRepository;
import lk.benjarong.ems.repository.SalaryRepository;
import lk.benjarong.ems.service.SalaryService;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MonthRepository monthRepository;

    @Override
    public List<SalaryEntity> getAllSalaries() {
        return salaryRepository.findAll();
    }

    @Override
    public SalaryEntity getSalaryById(Long id) {
        return salaryRepository.findById(id).orElse(null);
    }

    @Override
    public SalaryEntity saveSalary(SalaryDto salaryDto) {
        EmployeeEntity employeeEntity = employeeRepository.findById(salaryDto.getEmployeeId()).orElse(null);
        MonthEntity monthEntity = monthRepository.findById(salaryDto.getMonthId()).orElse(null);
        if (employeeEntity != null) {
            SalaryEntity salaryEntity = new SalaryEntity();
            salaryEntity.setDate(salaryDto.getDate());
            salaryEntity.setMonthEntity(monthEntity);
            salaryEntity.setBasicSalary(salaryDto.getBasicSalary()); // set basic salary
            salaryEntity.setBudgetaryReliefAllowance(salaryDto.getBudgetaryReliefAllowance()); // set budgetary relief allowance
            salaryEntity.setNoPay(salaryDto.getNoPay());
            salaryEntity.setTotalForEpf(salaryDto.getTotalForEpf());
            salaryEntity.setNormalOverTime(salaryDto.getNormalOverTime());
            salaryEntity.setDoubleOverTime(salaryDto.getDoubleOverTime());
            salaryEntity.setGrossSalary(salaryDto.getGrossSalary());
            salaryEntity.setEightPresentEpf(salaryDto.getEightPresentEpf());
            salaryEntity.setCashFloat(salaryDto.getCashFloat());
            salaryEntity.setStaffLoan(salaryDto.getStaffLoan());
            salaryEntity.setStaffDebtors(salaryDto.getStaffDebtors());
            salaryEntity.setSalaryAdvance(salaryDto.getSalaryAdvance());
            salaryEntity.setTotalDetuction(salaryDto.getTotalDetuction());
            salaryEntity.setBalancePay(salaryDto.getBalancePay());
            salaryEntity.setTwelvePresentEpf(salaryDto.getTwelvePresentEpf());
            salaryEntity.setThreePresentEtf(salaryDto.getThreePresentEtf());
            salaryEntity.setTwentyPresentEpf(salaryDto.getTwentyPresentEpf());
            salaryEntity.setFiftyPresentOnBasic(salaryDto.getFiftyPresentOnBasic());
            salaryEntity.setTotalSalary(salaryDto.getTotalSalary());
            salaryEntity.setEmployeeEntity(employeeEntity);
            return salaryRepository.save(salaryEntity);
        } else {
            return null;
        }
    }

    @Override
    public SalaryEntity updateSalary(Long id, SalaryDto salaryDto) {
        SalaryEntity existingSalary = salaryRepository.findById(id).orElse(null);
        if (existingSalary!=null) {
            existingSalary.setDate(salaryDto.getDate());
            existingSalary.setBasicSalary(salaryDto.getBasicSalary()); // equals employeeEntity?.basicSalary
            existingSalary.setBudgetaryReliefAllowance(salaryDto.getBudgetaryReliefAllowance());
            existingSalary.setNoPay(salaryDto.getNoPay());
            existingSalary.setTotalForEpf(salaryDto.getTotalForEpf());
            existingSalary.setNormalOverTime(salaryDto.getNormalOverTime());
            existingSalary.setDoubleOverTime(salaryDto.getDoubleOverTime());
            existingSalary.setGrossSalary(salaryDto.getGrossSalary());
            existingSalary.setEightPresentEpf(salaryDto.getEightPresentEpf());
            existingSalary.setCashFloat(salaryDto.getCashFloat());
            existingSalary.setStaffLoan(salaryDto.getStaffLoan());
            existingSalary.setStaffDebtors(salaryDto.getStaffDebtors());
            existingSalary.setSalaryAdvance(salaryDto.getSalaryAdvance());
            existingSalary.setTotalDetuction(salaryDto.getTotalDetuction());
            existingSalary.setBalancePay(salaryDto.getBalancePay());
            existingSalary.setTwelvePresentEpf(salaryDto.getTwelvePresentEpf());
            existingSalary.setThreePresentEtf(salaryDto.getThreePresentEtf());
            existingSalary.setTwentyPresentEpf(salaryDto.getTwentyPresentEpf());
            existingSalary.setFiftyPresentOnBasic(salaryDto.getFiftyPresentOnBasic());
            existingSalary.setTotalSalary(salaryDto.getTotalSalary());
            return salaryRepository.save(existingSalary);
        } else {
            return null;
        }
    }

    @Override
    public List<SalaryEntity> getSalariesByEmployee(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        if (employeeEntity!=null) {
            return salaryRepository.findSalariesByEmployee(employeeEntity);
        } else {
            return null;
        }
    }

    @Override
    public List<SalaryEntity> getSalariesByMonth(Long id) {
        MonthEntity monthEntity = monthRepository.findById(id).orElse(null);
        if (monthEntity!=null) {
            return salaryRepository.findSalariesByMonth(monthEntity);
        } else {
            return null;
        }
    }

    @Override
    public SalaryEntity deleteSalary(Long id) {
        SalaryEntity salaryEntity = salaryRepository.findById(id).orElse(null);
        if (salaryEntity!=null) {
            salaryRepository.delete(salaryEntity);
            return salaryEntity;
        } else {
            return null;
        }
    }

}
