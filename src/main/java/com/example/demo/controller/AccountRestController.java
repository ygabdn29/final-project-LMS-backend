package com.example.demo.controller;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("api/account")
public class AccountRestController {
  @Autowired
  private DepartmentService departmentService;

  @Autowired
  private UserService userService;
  
  @Autowired
  private RoleService roleService;

  @Autowired
  private EmailService emailService;
  
  @Autowired
  private EmployeeService employeeService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody User userLogin){
    User authenticatedUser = userService.authenticate(userLogin.getUsername(), userLogin.getPassword());

    if(authenticatedUser != null && !authenticatedUser.getIsVerified()){
      return Utils.generateResponseEntity(HttpStatus.OK, "Not Verified Yet!");
    }

    try{ 
      org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
      authenticatedUser.getId().toString(),
      "",
      getAuthorities(authenticatedUser.getRole().getName())
    );

    PreAuthenticatedAuthenticationToken authenticationToken = new PreAuthenticatedAuthenticationToken(
      user,
      "", 
      user.getAuthorities()
      );
    
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

    return Utils.generateResponseEntity(HttpStatus.OK, "Login Success!");
    } catch(Exception e){
      return Utils.generateResponseEntity(HttpStatus.OK, "Credentials Doesn't Match Any Records!");
    }
  }

  @PostMapping("/register")
  public ResponseEntity<Object> register(@RequestBody RegistrationDTO registrationDTO) {
    Department department = departmentService.get(registrationDTO.getDepartment_id());
    try {
      Employee employee = new Employee(null, registrationDTO.getFirstName(), registrationDTO.getLastName(), registrationDTO.getBirthDate(), registrationDTO.getGender(), registrationDTO.getAddress(), registrationDTO.getPhone(), registrationDTO.getEmail(), department);
      employeeService.save(employee);

      String username = registrationDTO.getFirstName() + "." + registrationDTO.getLastName();
      Role role = roleService.findByName("Mentee");
      String guid = UUID.randomUUID().toString();
      User user = new User(username, passwordEncoder.encode(registrationDTO.getPassword()) , null, employee, role, false);
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

  private static Collection<? extends GrantedAuthority> getAuthorities(String role){
    final List<SimpleGrantedAuthority> authorities = new LinkedList<>();
    authorities.add(new SimpleGrantedAuthority(role));
    return authorities;
  }

  @GetMapping("/mentors")
  public ResponseEntity<Object> getMentors() {
      try {
        List<User> mentors = userService.get().stream()
          .filter(user -> user.getRole().getName().equals("Mentor")) //filter only user mentor
          .collect(Collectors.toList());
        return Utils.generateResponseEntity(HttpStatus.OK, "Mentors accessed successfully", mentors);
    } catch (Exception e) {
        return Utils.generateResponseEntity(HttpStatus.OK, "Failed to access mentors: " + e.getMessage());
    }
  }
  
  @GetMapping("/verify/{guid}")
  public ResponseEntity<Object> verifyEmail(@PathVariable String guid) {
    // nanti ini yang diproses sama halaman react yang diakses dari link post register
    User user = userService.verifyUser(guid);
    if (user != null) {
      user.setIsVerified(true);
      user.setGuid(null);
      userService.save(user);
      return Utils.generateResponseEntity(HttpStatus.OK, "Verification Account successfully");
    }
    return Utils.generateResponseEntity(HttpStatus.BAD_REQUEST, "Verification Failed");
  }
  
  @GetMapping("/get-departments")
  public ResponseEntity<Object> getDepartments(){
    List<Department> departments = departmentService.get();
    return Utils.generateResponseEntity(HttpStatus.OK, "Departments Retrieved", departments);
  }
}
