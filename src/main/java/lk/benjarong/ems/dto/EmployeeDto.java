package lk.benjarong.ems.dto;

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
    private Double basicSalary;
    private Double budgetaryReliefAllowance;
    private Double travellingAllowance;
    private Double specialAllowance;
    private String bankname;
    private String bank;
    private String type;
    private Long departmentId;
}
