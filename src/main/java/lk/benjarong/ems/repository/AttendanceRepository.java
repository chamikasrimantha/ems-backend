package lk.benjarong.ems.repository;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import lk.benjarong.ems.entity.AttendanceEntity;
import lk.benjarong.ems.entity.EmployeeEntity;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long> {
    List<AttendanceEntity> findByEmployeeAndDateBetween(EmployeeEntity employee, Date startDate, Date endDate);

    boolean existsByEmployeeAndDate(EmployeeEntity employee, Date date);

    @Query("SELECT p FROM AttendanceEntity p WHERE p.employee = :employee")
    List<AttendanceEntity> findAttendancesByEmployee(@Param("employee") EmployeeEntity employee);

    // Get attendances by date range
    List<AttendanceEntity> findByDateBetween(Date startDate, Date endDate);
}
