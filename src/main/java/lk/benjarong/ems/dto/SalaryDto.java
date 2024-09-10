package lk.benjarong.ems.dto;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalaryDto {
    private Long id;
    private Date date;
    private Long monthId;
    private BigDecimal basicSalary;
    private BigDecimal budgetaryReliefAllowance;
    private BigDecimal noPay;
    private BigDecimal totalForEpf;
    private BigDecimal normalOverTime;
    private BigDecimal doubleOverTime;
    private BigDecimal grossSalary;
    private BigDecimal eightPresentEpf;
    private BigDecimal cashFloat;
    private BigDecimal staffLoan;
    private BigDecimal staffDebtors;
    private BigDecimal salaryAdvance;
    private BigDecimal totalDetuction;
    private BigDecimal balancePay;
    private BigDecimal twelvePresentEpf;
    private BigDecimal threePresentEtf;
    private BigDecimal twentyPresentEpf;
    private BigDecimal fiftyPresentOnBasic;
    private BigDecimal totalSalary;
    private BigDecimal noOfDays;
    private String sc;
    private BigDecimal travellingAllowance;
    private BigDecimal specialAllowance;
    private BigDecimal serviceCharges;
    private Long employeeId;
}
