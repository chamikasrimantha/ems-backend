package lk.benjarong.ems.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lk.benjarong.ems.dto.EmployeeDto;
import lk.benjarong.ems.entity.DepartmentEntity;
import lk.benjarong.ems.entity.EmployeeEntity;
import lk.benjarong.ems.entity.EmployeeType;
import lk.benjarong.ems.repository.DepartmentRepository;
import lk.benjarong.ems.repository.EmployeeRepository;
import lk.benjarong.ems.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public EmployeeEntity addEmployee(EmployeeDto employeeDto) {
        DepartmentEntity departmentEntity = departmentRepository.findById(employeeDto.getDepartmentId()).orElse(null);
        if (departmentEntity != null) {
            EmployeeEntity employeeEntity = new EmployeeEntity();
            employeeEntity.setSurname(employeeDto.getSurname());
            employeeEntity.setFirstname(employeeDto.getFirstname());
            employeeEntity.setLastname(employeeDto.getLastname());
            employeeEntity.setDob(employeeDto.getDob());
            employeeEntity.setAge(employeeDto.getAge());
            employeeEntity.setEmail(employeeDto.getEmail());
            employeeEntity.setNic(employeeDto.getNic());
            employeeEntity.setAddress(employeeDto.getAddress());
            employeeEntity.setEpf(employeeDto.getEpf());
            employeeEntity.setWef(employeeDto.getWef());
            employeeEntity.setDesignation(employeeDto.getDesignation());
            employeeEntity.setMobile(employeeDto.getMobile());
            employeeEntity.setBasicSalary(employeeDto.getBasicSalary());
            employeeEntity.setBudgetaryReliefAllowance(employeeDto.getBudgetaryReliefAllowance());
            employeeEntity.setType(EmployeeType.valueOf(employeeDto.getType().toUpperCase()));
            employeeEntity.setDepartmentEntity(departmentEntity);
            EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);

            // Send the email after saving the employee
            try {
                sendEmployeeCreationEmail(savedEmployee);
            } catch (MessagingException e) {
                e.printStackTrace(); // Handle the exception properly in a real application
            }

            return savedEmployee;
        } else {
            return null;
        }
    }

    private void sendEmployeeCreationEmail(EmployeeEntity employee) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(employee.getEmail()); // Assuming you have an email field in EmployeeEntity
        helper.setSubject("Testing Mail || Welcome to the Company");

        String content = "<div style='font-family: Arial, sans-serif; color: #333;'>"
                + "<h2 style='color: #007BFF;'>Welcome to the Team, " + employee.getFirstname() + "!</h2>"
                + "<p>Dear " + employee.getFirstname() + " " + employee.getLastname() + ",</p>"
                + "<p>We are pleased to inform you that you have been successfully added to the system as a new employee at "
                + employee.getDepartmentEntity().getName() + ".</p>"
                + "<p>Here are your details:</p>"
                + "<ul>"
                + "<li><strong>Name:</strong> " + employee.getFirstname() + " " + employee.getLastname() + "</li>"
                + "<li><strong>Designation:</strong> " + employee.getDesignation() + "</li>"
                + "<li><strong>Department:</strong> " + employee.getDepartmentEntity().getName() + "</li>"
                + "<li><strong>EPF Number:</strong> " + employee.getEpf() + "</li>"
                + "<li><strong>Basic salary:</strong> " + employee.getBasicSalary() + "</li>"
                + "</ul>"
                + "<p>We look forward to your contribution to the team.</p>"
                + "<p style='color: #007BFF;'>Best regards,<br>Human Resources</p>"
                + "<hr style='border: none; border-top: 1px solid #007BFF;'/>"
                + "<p style='font-size: 12px; color: #777;'>This is an automated message. Please do not reply.</p>"
                + "</div>";

        helper.setText(content, true);

        mailSender.send(message);
    }

    @Override
    public EmployeeEntity deleteEmployee(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        if (employeeEntity != null) {
            employeeRepository.delete(employeeEntity);
            return employeeEntity;
        } else {
            return null;
        }
    }

    @Override
    public List<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public EmployeeEntity getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public List<EmployeeEntity> getEmployeesByDepartment(Long id) {
        DepartmentEntity departmentEntity = departmentRepository.findById(id).orElse(null);
        if (departmentEntity != null) {
            return employeeRepository.findEmployeesByDepartment(departmentEntity);
        } else {
            return null;
        }
    }

    @Override
    public EmployeeEntity updateEmployee(Long id, EmployeeDto employeeDto) {
        EmployeeEntity existingEmployee = employeeRepository.findById(id).orElse(null);
        if (existingEmployee != null) {
            existingEmployee.setSurname(employeeDto.getSurname());
            existingEmployee.setFirstname(employeeDto.getFirstname());
            existingEmployee.setLastname(employeeDto.getLastname());
            existingEmployee.setDob(employeeDto.getDob());
            existingEmployee.setAge(employeeDto.getAge());
            existingEmployee.setNic(employeeDto.getNic());
            existingEmployee.setAddress(employeeDto.getAddress());
            existingEmployee.setEpf(employeeDto.getEpf());
            existingEmployee.setWef(employeeDto.getWef());
            existingEmployee.setDesignation(employeeDto.getDesignation());
            existingEmployee.setMobile(employeeDto.getMobile());
            existingEmployee.setBasicSalary(employeeDto.getBasicSalary());
            existingEmployee.setBudgetaryReliefAllowance(employeeDto.getBudgetaryReliefAllowance());
            existingEmployee.setType(EmployeeType.valueOf(employeeDto.getType().toUpperCase()));
            return employeeRepository.save(existingEmployee);
        } else {
            return null;
        }
    }

}
