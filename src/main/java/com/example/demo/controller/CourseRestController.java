package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.handler.Utils;
import com.example.demo.model.Course;
import com.example.demo.model.User;
import com.example.demo.model.dto.NewCourseDTO;
import com.example.demo.service.CourseService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("api/course")
public class CourseRestController {
  @Autowired
  private UserService userService;

  @Autowired
  private CourseService courseService;



  @PostMapping("/create")
  public ResponseEntity<Object> newCourse(@RequestBody NewCourseDTO newCourseDTO) {
    User mentor = userService.get(newCourseDTO.getMentorId());

    try {
      Course course = new Course(null, newCourseDTO.getName(), newCourseDTO.getDescription(), newCourseDTO.getQuota(), newCourseDTO.getBegin(), newCourseDTO.getEnd(), mentor);
      courseService.save(course);

      return Utils.generateResponseEntity(HttpStatus.OK, "Course successfully created");
    } catch (Exception e) {
      return Utils.generateResponseEntity(HttpStatus.OK, "Failed to create course: " + e.getMessage());
    }
  }
}
