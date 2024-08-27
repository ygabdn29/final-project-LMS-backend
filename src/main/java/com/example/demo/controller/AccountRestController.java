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
  private DepartmentService departmentService;

  @Autowired
  private UserService userService;

  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody User userLogin){
    User authenticatedUser = userService.authenticate(userLogin.getUsername(), userLogin.getPassword());

    if(authenticatedUser == null){
      return Utils.generateResponseEntity(HttpStatus.OK, "Login Failed!");
    }

    return Utils.generateResponseEntity(HttpStatus.OK, "Login Success!");
  }

  @PostMapping("/register")
  public ResponseEntity<Object> register(@RequestBody RegistrationDTO registrationDTO) {
    Department department = departmentService.get(registrationDTO.getDepartment_id());
    try {
      Employee employee = new Employee(null, registrationDTO.getFirstName(), registrationDTO.getMiddleName(), registrationDTO.getLastName(), registrationDTO.getBirthDate(), registrationDTO.getGender(), registrationDTO.getAddress(), registrationDTO.getPhone(), registrationDTO.getEmail(), department);
      employeeService.save(employee);

      String username = registrationDTO.getFirstName() + "." + registrationDTO.getLastName();
      // default isAdmin is false,
      User user = new User(username, passwordEncoder.encode(registrationDTO.getPassword()), false, null, employee);
      userService.save(user);

      return Utils.generateResponseEntity(HttpStatus.OK, "Registration Successful.");
    } catch (Exception e) {
      return Utils.generateResponseEntity(HttpStatus.OK, "Registration Failed: " + e.getMessage());
    }
  }
}
