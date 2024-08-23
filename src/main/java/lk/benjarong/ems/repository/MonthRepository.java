package lk.benjarong.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lk.benjarong.ems.entity.MonthEntity;

@Repository
public interface MonthRepository extends JpaRepository<MonthEntity, Long>{
    
}
