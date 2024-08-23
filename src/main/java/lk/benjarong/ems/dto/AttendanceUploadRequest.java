package lk.benjarong.ems.dto;

import java.sql.Date;
import java.util.List;

public class AttendanceUploadRequest {
    private List<AttendanceRecord> attendanceRecords;

    public List<AttendanceRecord> getAttendanceRecords() {
        return attendanceRecords;
    }

    public void setAttendanceRecords(List<AttendanceRecord> attendanceRecords) {
        this.attendanceRecords = attendanceRecords;
    }

    public static class AttendanceRecord {
        private Long employeeId;
        private String employeeName; // Optional, for validation purposes
        private List<DailyAttendance> dailyAttendanceList;

        public Long getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(Long employeeId) {
            this.employeeId = employeeId;
        }

        public String getEmployeeName() {
            return employeeName;
        }

        public void setEmployeeName(String employeeName) {
            this.employeeName = employeeName;
        }

        public List<DailyAttendance> getDailyAttendanceList() {
            return dailyAttendanceList;
        }

        public void setDailyAttendanceList(List<DailyAttendance> dailyAttendanceList) {
            this.dailyAttendanceList = dailyAttendanceList;
        }

        public static class DailyAttendance {
            private Date date;
            private boolean isPresent;
            private Double hours;

            public Double getHours() {
                return hours;
            }

            public void setHours(Double hours) {
                this.hours = hours;
            }

            public Date getDate() {
                return date;
            }

            public void setDate(Date date) {
                this.date = date;
            }

            public boolean isPresent() {
                return isPresent;
            }

            public void setPresent(boolean isPresent) {
                this.isPresent = isPresent;
            }
        }
    }
}
