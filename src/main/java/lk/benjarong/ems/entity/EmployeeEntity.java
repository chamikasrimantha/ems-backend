package lk.benjarong.ems.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeId")
    private Long id;

    private String surname;

    private String firstname;

    private String lastname;

    private Date dob;  // format == YYYY-mm-dd

    private String email;

    private Long age;

    private String nic;

    private String address;

    private Long epf;

    private Date wef; // date joined

    private String designation;

    private String mobile;

    private BigDecimal basicSalary; // basic salary

    private BigDecimal budgetaryReliefAllowance; // budgetary relief allowance

    private BigDecimal travellingAllowance; // travelling allowance
 
    private BigDecimal specialAllowance; // special allowance

    private String bankname; // bank name

    private String bank; // bank account number

    @Enumerated(EnumType.STRING)
    private EmployeeType type; // Probation or Permanent

    // many to one relationship with department entity
    @ManyToOne
    @JoinColumn(name = "departmentId")
    private DepartmentEntity departmentEntity;

    // one to many with salary entity
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employeeEntity", cascade = CascadeType.ALL)
    private List<SalaryEntity> salaries;

}
