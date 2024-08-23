package lk.benjarong.ems.service;

import org.springframework.web.multipart.MultipartFile;

import lk.benjarong.ems.entity.AttendanceEntity;
import lk.benjarong.ems.entity.EmployeeEntity;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

public interface AttendanceService {
    void uploadAttendance(MultipartFile file) throws IOException;
    List<AttendanceEntity> getAttendancesByEmployee(Long id);
    // List<AttendanceEntity> getAttendancesByMonth(Long id);
    List<AttendanceEntity> getAttendanceByEmployeeAndDateRange(EmployeeEntity employee, Date startDate, Date endDate);
    List<AttendanceEntity> getAttendanceByDateRange(Date startDate, Date endDate);
    List<AttendanceEntity> getAllAttendances();
}
