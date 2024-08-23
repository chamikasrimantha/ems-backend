package lk.benjarong.ems.entity;

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
    
    private Double basicSalary; // basic salary == employeeEntity?.basicSalary

    private Double budgetaryReliefAllowance;  // budgetary relief allowance == employeeEntity?.budgetaryReliefAllowance

    private Double noPay; // noPay === ((basicSalary + budgetaryReliefAllowance) / 30) * 2

    private Double totalForEpf; // totalForEpf == (basicSalary + budgetaryReliefAllowance) - noPay

    private Double normalOverTime; // normalOT == if OT hours >= 24 then == ((grossSalary / 240) * 24 hrs) * 1.5

    private Double doubleOverTime; // double OT == if worked in holidays then == ((grossSalary / 240) * 8 hrs) * 2

    private Double grossSalary; // gross salary == (basicSalary + budgetaryReliefAllowance) - noPay

    private Double eightPresentEpf; // 8% epf

    private Double cashFloat; // manually

    private Double staffLoan; // manually

    private Double staffDebtors; // manually

    private Double salaryAdvance; // manually

    private Double totalDetuction; // totalDetuction == 8% epf + cashFloat + staffLoan + staffDebtors + salaryAdvance

    private Double balancePay; // balancePay == grossSalary - totalDetuction

    private Double twelvePresentEpf; // 12% epf

    private Double threePresentEtf; // 3% etf

    private Double twentyPresentEpf; // 20% epf

    private Double fiftyPresentOnBasic; // (basicSalary + budgetaryReliefAllowance) / 2

    private Double totalSalary; 

    // many to one relationship with employee entity
    @ManyToOne
    @JoinColumn(name = "employeeId")
    private EmployeeEntity employeeEntity;

    @ManyToOne
    @JoinColumn(name = "monthId")
    private MonthEntity monthEntity;

}
