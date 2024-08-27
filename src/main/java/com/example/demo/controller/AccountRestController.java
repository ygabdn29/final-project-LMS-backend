package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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

  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody User userLogin){
    User authenticatedUser = userService.authenticate(userLogin.getUsername(), userLogin.getPassword());

    if(authenticatedUser == null){
      return Utils.generateResponseEntity(HttpStatus.OK, "Login Failed!");
    }

    return Utils.generateResponseEntity(HttpStatus.OK, "Login Success!");
  }
}
