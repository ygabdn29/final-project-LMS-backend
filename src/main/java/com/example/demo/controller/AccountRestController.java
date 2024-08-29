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

  private static Collection<? extends GrantedAuthority> getAuthorities(String role){
    final List<SimpleGrantedAuthority> authorities = new LinkedList<>();
    authorities.add(new SimpleGrantedAuthority(role));
    return authorities;
  }
}
