package lk.benjarong.ems.entity;

import java.sql.Date;
import jakarta.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@Table(name = "attendance")
public class AttendanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendanceId")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employeeId", nullable = false)
    private EmployeeEntity employee;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private boolean isPresent; // under this there should be the time also --> 
                                    // to check normalOverTime & doubleOverTime

    @Column(nullable = false)
    private Double hours;

}
