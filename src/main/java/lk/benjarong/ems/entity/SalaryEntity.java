package lk.benjarong.ems.entity;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@Table(name = "salary")
public class SalaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salaryId")
    private Long id;

    private Date date; // pay date

    // private Month month;
    
    private BigDecimal basicSalary; // basic salary == employeeEntity?.basicSalary

    private BigDecimal budgetaryReliefAllowance;  // budgetary relief allowance == employeeEntity?.budgetaryReliefAllowance

    private BigDecimal noPay; // noPay === ((basicSalary + budgetaryReliefAllowance) / 30) * 2

    private BigDecimal totalForEpf; // totalForEpf == (basicSalary + budgetaryReliefAllowance) - noPay

    private BigDecimal normalOverTime; // normalOT == if OT hours >= 24 then == ((grossSalary / 240) * 24 hrs) * 1.5

    private BigDecimal doubleOverTime; // double OT == if worked in holidays then == ((grossSalary / 240) * 8 hrs) * 2

    private BigDecimal grossSalary; // gross salary == (basicSalary + budgetaryReliefAllowance) - noPay

    private BigDecimal eightPresentEpf; // 8% epf

    private BigDecimal cashFloat; // manually

    private BigDecimal staffLoan; // manually

    private BigDecimal staffDebtors; // manually

    private BigDecimal salaryAdvance; // manually

    private BigDecimal totalDetuction; // totalDetuction == 8% epf + cashFloat + staffLoan + staffDebtors + salaryAdvance

    private BigDecimal balancePay; // balancePay == grossSalary - totalDetuction

    private BigDecimal twelvePresentEpf; // 12% epf

    private BigDecimal threePresentEtf; // 3% etf

    private BigDecimal twentyPresentEpf; // 20% epf

    private BigDecimal fiftyPresentOnBasic; // (basicSalary + budgetaryReliefAllowance) / 2

    private BigDecimal noOfDays; // 30 - (noPayDays + leaveDays)

    private String sc; // percentage

    private BigDecimal travellingAllowance; // travelling allowance

    private BigDecimal specialAllowance; // special allowance

    private BigDecimal serviceCharges; // service charges

    private BigDecimal totalSalary; // total salary = balancePay + travellingAllowance + specialAllowance + serviceCharges

    // many to one relationship with employee entity
    @ManyToOne
    @JoinColumn(name = "employeeId")
    private EmployeeEntity employeeEntity;

    @ManyToOne
    @JoinColumn(name = "monthId")
    private MonthEntity monthEntity;

}
