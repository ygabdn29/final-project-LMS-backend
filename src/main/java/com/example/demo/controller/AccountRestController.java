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
import com.example.demo.model.Course;
import com.example.demo.model.CourseUserRole;
import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.model.User;
import com.example.demo.model.dto.NewCourseDTO;
import com.example.demo.model.dto.RegistrationDTO;
import com.example.demo.service.CourseService;
import com.example.demo.service.CourseUserRoleService;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.RoleService;
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

  @Autowired
  private CourseService courseService;

  @Autowired
  private RoleService roleService;

  @Autowired
  private CourseUserRoleService courseUserRoleService;

  @PostMapping("/register")
  public ResponseEntity<Object> register(@RequestBody RegistrationDTO registrationDTO) {
    Department department = departmentService.get(registrationDTO.getDepartment_id());
    try {
      Employee employee = new Employee(null, registrationDTO.getBirthDate(), registrationDTO.getFirstName(),
          registrationDTO.getLastName(), registrationDTO.getGender(), registrationDTO.getAddress(),
          registrationDTO.getPhone(), registrationDTO.getEmail(), department);

      employeeService.save(employee);
      String username = registrationDTO.getFirstName() + "." + registrationDTO.getLastName();
      User user = new User(username, passwordEncoder.encode(registrationDTO.getPassword()), employee);
      userService.save(user);

      return Utils.generateResponseEntity(HttpStatus.OK, "Registration Successful.");
    } catch (Exception e) {
      return Utils.generateResponseEntity(HttpStatus.OK, "Registration Failed: " + e.getMessage());
    }
  }

  @PostMapping("/new-course")
  public ResponseEntity<Object> newCourse(@RequestBody NewCourseDTO newCourseDTO) {
    User mentor = userService.get(newCourseDTO.getMentorId());

    try {
      Course course = new Course(null, newCourseDTO.getName(), newCourseDTO.getBegin(), newCourseDTO.getEnd());
      courseService.save(course);

      CourseUserRole courseUserRole = new CourseUserRole(mentor, course, roleService.getRoleWithLowestLevel());
      courseUserRoleService.save(courseUserRole);

      return Utils.generateResponseEntity(HttpStatus.OK, "Registration Successful.");
    } catch (Exception e) {
      return Utils.generateResponseEntity(HttpStatus.OK, "Registration Failed: " + e.getMessage());
    }
  }

}
