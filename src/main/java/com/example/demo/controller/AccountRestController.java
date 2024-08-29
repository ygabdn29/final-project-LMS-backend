package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.handler.Utils;
import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.dto.RegistrationDTO;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.EmailService;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import java.util.UUID;



@RestController
@RequestMapping("api/account")
public class AccountRestController {
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private EmployeeService employeeService;
  
  @Autowired
  private DepartmentService departmentService;

  @Autowired
  private UserService userService;

  @Autowired
  private RoleService roleService;

  @Autowired
  private EmailService emailService;

  @PostMapping("/register")
  public ResponseEntity<Object> register(@RequestBody RegistrationDTO registrationDTO) {
    Department department = departmentService.get(registrationDTO.getDepartment_id());
    try {
      Employee employee = new Employee(null, registrationDTO.getFirstName(), registrationDTO.getMiddleName(), registrationDTO.getLastName(), registrationDTO.getBirthDate(), registrationDTO.getGender(), registrationDTO.getAddress(), registrationDTO.getPhone(), registrationDTO.getEmail(), department);
      employeeService.save(employee);

      String username = registrationDTO.getFirstName() + "." + registrationDTO.getLastName();
      Role role = roleService.findByName("Mentee");
      String guid = UUID.randomUUID().toString();
      User user = new User(username, passwordEncoder.encode(registrationDTO.getPassword()) , null, employee, role);
      user.setGuid(guid);
      userService.save(user);

      String subject = "Email Verification";
      // Ini harus diubah ke link halaman react
      String confirmationUrl = "http://localhost:3000/verify/" + user.getGuid();
      String message = "Click the link to verify your email: \n" + confirmationUrl;
      emailService.sendEmail(employee.getEmail(), subject, message);

      return Utils.generateResponseEntity(HttpStatus.OK, "Registration Successful. A verification email has been sent to your email address.");
    } catch (Exception e) {
      return Utils.generateResponseEntity(HttpStatus.OK, "Registration Failed: " + e.getMessage());
    }
  }
}
