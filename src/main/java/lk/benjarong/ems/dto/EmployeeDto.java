package lk.benjarong.ems.dto;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
    private String surname;
    private String firstname;
    private String lastname;
    private Date dob;
    private Long age;
    private String email;
    private String nic;
    private String address;
    private Long epf;
    private Date wef;
    private String designation;
    private String mobile;
    private BigDecimal basicSalary;
    private BigDecimal budgetaryReliefAllowance;
    private BigDecimal travellingAllowance;
    private BigDecimal specialAllowance;
    private String bankname;
    private String bank;
    private String type;
    private Long departmentId;
}
