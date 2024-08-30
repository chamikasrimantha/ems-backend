package lk.benjarong.ems.service.impl;

import lk.benjarong.ems.dto.AttendanceUploadRequest.AttendanceRecord;
import lk.benjarong.ems.dto.AttendanceUploadRequest.AttendanceRecord.DailyAttendance;
import lk.benjarong.ems.entity.AttendanceEntity;
import lk.benjarong.ems.entity.EmployeeEntity;
import lk.benjarong.ems.repository.AttendanceRepository;
import lk.benjarong.ems.repository.EmployeeRepository;
import lk.benjarong.ems.service.AttendanceService;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    // @Override
    // public void uploadAttendance(MultipartFile file) throws IOException {
    // // Parse the uploaded Excel file
    // List<AttendanceRecord> attendanceRecords = parseExcelFile(file);

    // // Save the attendance records to the database
    // for (AttendanceRecord record : attendanceRecords) {
    // Long employeeId = record.getEmployeeId();
    // EmployeeEntity employee = employeeRepository.findById(employeeId)
    // .orElseThrow(() -> new IllegalArgumentException("Employee not found with ID:
    // " + employeeId));

    // for (DailyAttendance dailyAttendance : record.getDailyAttendanceList()) {
    // AttendanceEntity attendanceEntity = new AttendanceEntity();
    // attendanceEntity.setEmployee(employee);
    // attendanceEntity.setDate(dailyAttendance.getDate());
    // attendanceEntity.setPresent(dailyAttendance.isPresent());

    // attendanceRepository.save(attendanceEntity);
    // }
    // }
    // }

    // private List<AttendanceRecord> parseExcelFile(MultipartFile file) throws
    // IOException {
    // List<AttendanceRecord> attendanceRecords = new ArrayList<>();

    // Workbook workbook = new XSSFWorkbook(file.getInputStream());
    // Sheet sheet = workbook.getSheetAt(0);

    // // Get the header row (dates)
    // Iterator<Row> rows = sheet.iterator();
    // Row headerRow = rows.next();

    // List<Date> dates = new ArrayList<>();
    // for (Cell cell : headerRow) {
    // if (cell.getColumnIndex() != 0) { // Skip the "Employee ID" column
    // Date date = null;
    // if (cell.getCellType() == CellType.NUMERIC) {
    // // Excel stores dates as numeric values
    // date = new Date(cell.getDateCellValue().getTime());
    // } else if (cell.getCellType() == CellType.STRING) {
    // date = Date.valueOf(cell.getStringCellValue());
    // }
    // dates.add(date);
    // }
    // }

    // // Iterate over rows and create AttendanceRecord objects
    // while (rows.hasNext()) {
    // Row currentRow = rows.next();
    // AttendanceRecord record = new AttendanceRecord();

    // Long employeeId = (long) currentRow.getCell(0).getNumericCellValue();
    // record.setEmployeeId(employeeId);

    // List<DailyAttendance> dailyAttendanceList = new ArrayList<>();
    // for (int i = 1; i < currentRow.getLastCellNum(); i++) {
    // DailyAttendance dailyAttendance = new DailyAttendance();
    // dailyAttendance.setDate(dates.get(i - 1)); // Subtract 1 because dates start
    // from the second column
    // dailyAttendance.setPresent(currentRow.getCell(i).getBooleanCellValue());

    // dailyAttendanceList.add(dailyAttendance);
    // }

    // record.setDailyAttendanceList(dailyAttendanceList);
    // attendanceRecords.add(record);
    // }

    // workbook.close();
    // return attendanceRecords;
    // }

    @Override
    public void uploadAttendance(MultipartFile file) throws IOException {
        // Parse the uploaded Excel file
        List<AttendanceRecord> attendanceRecords = parseExcelFile(file);

        // Save the attendance records to the database
        for (AttendanceRecord record : attendanceRecords) {
            Long employeeId = record.getEmployeeId();
            EmployeeEntity employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new IllegalArgumentException("Employee not found with ID: " + employeeId));

            for (DailyAttendance dailyAttendance : record.getDailyAttendanceList()) {
                AttendanceEntity attendanceEntity = new AttendanceEntity();
                attendanceEntity.setEmployee(employee);
                attendanceEntity.setDate(dailyAttendance.getDate());
                attendanceEntity.setIsPresent(dailyAttendance.isPresent());

                // Save the hours worked for the day, if necessary
                attendanceEntity.setHours(dailyAttendance.getHours());

                attendanceRepository.save(attendanceEntity);
            }
        }
    }

    private List<AttendanceRecord> parseExcelFile(MultipartFile file) throws IOException {
        List<AttendanceRecord> attendanceRecords = new ArrayList<>();

        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rows = sheet.iterator();
        Row headerRow = rows.next(); // Skip the header row

        List<Date> dates = new ArrayList<>();
        for (Cell cell : headerRow) {
            if (cell.getColumnIndex() != 0) { // Skip the "Employee ID" column
                Date date = null;
                if (cell.getCellType() == CellType.NUMERIC) {
                    // Excel stores dates as numeric values
                    date = new Date(cell.getDateCellValue().getTime());
                } else if (cell.getCellType() == CellType.STRING) {
                    date = Date.valueOf(cell.getStringCellValue());
                }
                dates.add(date);
            }
        }

        // Iterate over rows in pairs: one for presence, one for hours
        while (rows.hasNext()) {
            Row presenceRow = rows.next();
            Row hoursRow = rows.hasNext() ? rows.next() : null; // Ensure there's a corresponding hours row

            AttendanceRecord record = new AttendanceRecord();
            Long employeeId = (long) presenceRow.getCell(0).getNumericCellValue();
            record.setEmployeeId(employeeId);

            List<DailyAttendance> dailyAttendanceList = new ArrayList<>();
            for (int i = 1; i < presenceRow.getLastCellNum(); i++) {
                DailyAttendance dailyAttendance = new DailyAttendance();
                dailyAttendance.setDate(dates.get(i - 1)); // Date corresponding to the column
                dailyAttendance.setPresent(presenceRow.getCell(i).getBooleanCellValue());

                if (hoursRow != null) {
                    dailyAttendance.setHours(hoursRow.getCell(i).getNumericCellValue()); // Set hours worked
                }

                dailyAttendanceList.add(dailyAttendance);
            }

            record.setDailyAttendanceList(dailyAttendanceList);
            attendanceRecords.add(record);
        }

        workbook.close();
        return attendanceRecords;
    }

    @Override
    public List<AttendanceEntity> getAttendancesByEmployee(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        if (employeeEntity != null) {
            return attendanceRepository.findAttendancesByEmployee(employeeEntity);
        } else {
            return null;
        }
    }

    @Override
    public List<AttendanceEntity> getAttendanceByDateRange(Date startDate, Date endDate) {
        return attendanceRepository.findByDateBetween(startDate, endDate);
    }

    @Override
    public List<AttendanceEntity> getAttendanceByEmployeeAndDateRange(EmployeeEntity employee, Date startDate,
            Date endDate) {
        return attendanceRepository.findByEmployeeAndDateBetween(employee, startDate, endDate);
    }

    @Override
    public List<AttendanceEntity> getAllAttendances() {
        return attendanceRepository.findAll();
    }

}
