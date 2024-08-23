package lk.benjarong.ems.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lk.benjarong.ems.entity.MonthEntity;

@Service
public interface MonthService {
    MonthEntity addMonth(MonthEntity monthEntity);
    List<MonthEntity> getAllMonths();
    MonthEntity updateMonth(Long id, MonthEntity monthEntity);
    MonthEntity deleteMonth(Long id);
}
