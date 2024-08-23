package lk.benjarong.ems.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalaryDto {
    private Long id;
    private Date date;
    private Long monthId;
    private Double basicSalary;
    private Double budgetaryReliefAllowance;
    private Double noPay;
    private Double totalForEpf;
    private Double normalOverTime;
    private Double doubleOverTime;
    private Double grossSalary;
    private Double eightPresentEpf;
    private Double cashFloat;
    private Double staffLoan;
    private Double staffDebtors;
    private Double salaryAdvance;
    private Double totalDetuction;
    private Double balancePay;
    private Double twelvePresentEpf;
    private Double threePresentEtf;
    private Double twentyPresentEpf;
    private Double fiftyPresentOnBasic;
    private Double totalSalary;
    private Long employeeId;
}
