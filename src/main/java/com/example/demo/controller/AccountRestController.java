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
import com.example.demo.model.User;
import com.example.demo.model.dto.RegistrationDTO;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("api/account")
public class AccountRestController {
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private EmployeeService employeeService;

  @Autowired
  private UserService userService;

  @Autowired
  private DepartmentService departmentService;

  @PostMapping("/register")
  public ResponseEntity<Object> register(@RequestBody RegistrationDTO registrationDTO){
    Department department = departmentService.get(registrationDTO.getDepartment_id());
    try{
      Employee employee = new Employee(null, registrationDTO.getBirthDate(), registrationDTO.getFirstName(), registrationDTO.getLastName(), registrationDTO.getGender(), registrationDTO.getAddress(), registrationDTO.getPhone(), registrationDTO.getEmail(), department);

      employeeService.save(employee);
      String username = registrationDTO.getFirstName() + "." + registrationDTO.getLastName();
      User user = new User(username, passwordEncoder.encode(registrationDTO.getPassword()), null, employee);
      userService.save(user);
 
      return Utils.generateResponseEntity(HttpStatus.OK, "Registration Successful.");
    }catch (Exception e) {
      return Utils.generateResponseEntity(HttpStatus.OK,
          "Registration Failed: " + e.getMessage());
    }
  }
}
