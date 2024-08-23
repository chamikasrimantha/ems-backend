package lk.benjarong.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lk.benjarong.ems.entity.MonthEntity;
import lk.benjarong.ems.service.MonthService;

@RestController
@CrossOrigin(origins = "*")
public class MonthController {

    @Autowired
    private MonthService monthService;

    @PostMapping("/month")
    public MonthEntity addMonth(@RequestBody MonthEntity monthEntity) {
        return monthService.addMonth(monthEntity);
    }

    @GetMapping("/month")
    public List<MonthEntity> getAllMonths() {
        return monthService.getAllMonths();
    }

    @DeleteMapping("/month/{id}")
    public MonthEntity deleteMonth(@PathVariable Long id) {
        return monthService.deleteMonth(id);
    }

    @PutMapping("/month/{id}")
    public MonthEntity updateMonth(@PathVariable Long id, @RequestBody MonthEntity monthEntity) {
        return monthService.updateMonth(id, monthEntity);
    }
    
}
