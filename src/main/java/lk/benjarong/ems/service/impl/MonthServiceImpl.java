package lk.benjarong.ems.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lk.benjarong.ems.entity.MonthEntity;
import lk.benjarong.ems.repository.MonthRepository;
import lk.benjarong.ems.service.MonthService;

@Service
public class MonthServiceImpl implements MonthService {

    @Autowired
    private MonthRepository monthRepository;

    @Override
    public MonthEntity addMonth(MonthEntity monthEntity) {
        return monthRepository.save(monthEntity);
    }

    @Override
    public MonthEntity deleteMonth(Long id) {
        MonthEntity monthEntity = monthRepository.findById(id).orElse(null);
        if (monthEntity != null) {
            monthRepository.delete(monthEntity);
            return monthEntity;
        } else {
            return null;
        }
    }

    @Override
    public List<MonthEntity> getAllMonths() {
        return monthRepository.findAll();
    }

    @Override
    public MonthEntity updateMonth(Long id, MonthEntity monthEntity) {
        MonthEntity existingMonth = monthRepository.findById(id).orElse(null);
        if (existingMonth!=null) {
            existingMonth.setName(monthEntity.getName());
            return monthRepository.save(existingMonth);
        } else {
            return null;
        }
    }

}
