package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.handler.Utils;
import com.example.demo.model.Course;
import com.example.demo.service.CourseService;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api/course")
public class CourseRestController {
  @Autowired
  private CourseService courseService;
  


  @GetMapping
  public ResponseEntity<Object> accessCourse(@RequestHeader Integer id) {
    try {
      Course course = courseService.get(id);
      if (course == null) {
        return Utils.generateResponseEntity(HttpStatus.OK, "Course not found");
      }
      return Utils.generateResponseEntity(HttpStatus.OK, "Course accessed successfully", course);
    } catch (Exception e) {
      return Utils.generateResponseEntity(HttpStatus.OK, "Failed to access course: " + e.getMessage());
    }
  }


  @DeleteMapping("delete")
  public ResponseEntity<Object> deleteCourse(@RequestHeader Integer id) {
    try {
      courseService.delete(id);
      return Utils.generateResponseEntity(HttpStatus.OK, "Course deleted successfully");
    } catch (Exception e) {
      return Utils.generateResponseEntity(HttpStatus.OK, "Failed to delete course: " + e.getMessage());
    }
  }
}
