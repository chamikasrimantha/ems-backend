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

import lk.benjarong.ems.dto.SalaryDto;
import lk.benjarong.ems.entity.SalaryEntity;
import lk.benjarong.ems.service.SalaryService;

@RestController
@CrossOrigin(origins = "*")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @PostMapping("/salaries")
    public ResponseEntity<SalaryEntity> saveSalary(@RequestBody SalaryDto salaryDto) {
        try {
            return ResponseEntity.status(200).body(salaryService.saveSalary(salaryDto));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/salaries")
    public ResponseEntity<List<SalaryEntity>> getAllSalaries() {
        List<SalaryEntity> salaries = salaryService.getAllSalaries();
        if (salaries != null) {
            return ResponseEntity.status(200).body(salaries);
        } else {
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/salaries/{id}")
    public ResponseEntity<SalaryEntity> getSalariesById(@PathVariable Long id) {
        SalaryEntity salaryEntity = salaryService.getSalaryById(id);
        if (salaryEntity != null) {
            return ResponseEntity.status(HttpStatus.OK).body(salaryEntity);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/salaries/{id}")
    public SalaryEntity updateSalary(@PathVariable Long id, @RequestBody SalaryDto salaryDto){
        return salaryService.updateSalary(id, salaryDto);
    }

    @GetMapping("/employees/{id}/salaries")
    public ResponseEntity<List<SalaryEntity>> getSalariesByEmployee(@PathVariable Long id){
        return ResponseEntity.ok().body(salaryService.getSalariesByEmployee(id));
    }

    @GetMapping("/month/{id}/salaries")
    public ResponseEntity<List<SalaryEntity>> getSalariesByMonth(@PathVariable Long id) {
        return ResponseEntity.ok().body(salaryService.getSalariesByMonth(id));
    }

    @DeleteMapping("/salaries/{id}")
    public SalaryEntity deleteSalary(@PathVariable Long id) {
        return salaryService.deleteSalary(id);
    }

}
