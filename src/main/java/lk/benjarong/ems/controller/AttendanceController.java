package lk.benjarong.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import lk.benjarong.ems.entity.AttendanceEntity;
import lk.benjarong.ems.entity.EmployeeEntity;
import lk.benjarong.ems.service.AttendanceService;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/attendance")
    public ResponseEntity<String> uploadAttendanceFile(@RequestParam("file") MultipartFile file) {
        try {
            attendanceService.uploadAttendance(file);
            return ResponseEntity.ok("Attendance data uploaded successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload attendance data.");
        }
    }

    @GetMapping("/attendance")
    public List<AttendanceEntity> getAllAttendances() {
        return attendanceService.getAllAttendances();
    }

    // Get attendances by employee ID
    @GetMapping("/employees/{id}/attendance")
    public List<AttendanceEntity> getAttendancesByEmployee(@PathVariable Long id) {
        return attendanceService.getAttendancesByEmployee(id);
    }

    // Get attendances by month ID
    // @GetMapping("/month/{id}")
    // public List<AttendanceEntity> getAttendancesByMonth(@PathVariable Long id) {
    //     return attendanceService.getAttendancesByMonth(id);
    // }

    // Get attendances by employee and date range
    @GetMapping("/attendance/employee/{id}/range")
    public List<AttendanceEntity> getAttendanceByEmployeeAndDateRange(
            @PathVariable Long id,
            @RequestParam Date startDate,
            @RequestParam Date endDate) {

        // You might need to fetch EmployeeEntity using the ID
        // For simplicity, this example assumes you have a method to get EmployeeEntity
        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(id); // Set the ID, you may need to fetch it from the database

        return attendanceService.getAttendanceByEmployeeAndDateRange(employee, startDate, endDate);
    }

    // Get attendances by date range
    @GetMapping("/attendance/range")
    public List<AttendanceEntity> getAttendanceByDateRange(
            @RequestParam Date startDate,
            @RequestParam Date endDate) {
        return attendanceService.getAttendanceByDateRange(startDate, endDate);
    }
}
