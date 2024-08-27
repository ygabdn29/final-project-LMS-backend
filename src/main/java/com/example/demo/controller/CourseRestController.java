package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.handler.Utils;
import com.example.demo.model.Course;
import com.example.demo.model.User;
import com.example.demo.model.dto.NewCourseDTO;
import com.example.demo.service.CourseService;
import com.example.demo.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api/course")
public class CourseRestController {
  @Autowired
  private UserService userService;

  @Autowired
  private CourseService courseService;

  @PostMapping("create")
  public ResponseEntity<Object> newCourse(@RequestBody NewCourseDTO newCourseDTO) {
    User mentor = userService.get(newCourseDTO.getMentorId());
    try {
      Course course = new Course(null, newCourseDTO.getName(), newCourseDTO.getDescription(), newCourseDTO.getQuota(),
          newCourseDTO.getBegin(), newCourseDTO.getEnd(), mentor);
      courseService.save(course);
      return Utils.generateResponseEntity(HttpStatus.OK, "Course successfully created");
    } catch (Exception e) {
      return Utils.generateResponseEntity(HttpStatus.OK, "Failed to create course: " + e.getMessage());
    }
  }

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

  @PostMapping("update")
  public ResponseEntity<Object> updateCourse(@RequestBody NewCourseDTO newCourseDTO) {
    User newMentor = userService.get(newCourseDTO.getMentorId());
    Course course = courseService.get(newCourseDTO.getId());
    try {
      if (course == null) {
        return Utils.generateResponseEntity(HttpStatus.OK, "Course not found");
      }
      Course updatedCourse = new Course(newCourseDTO.getId(), newCourseDTO.getName(), newCourseDTO.getDescription(), newCourseDTO.getQuota(),
      newCourseDTO.getBegin(), newCourseDTO.getEnd(), newMentor);
      courseService.save(updatedCourse);
      return Utils.generateResponseEntity(HttpStatus.OK, "Course updated successfully");
    } catch (Exception e) {
      return Utils.generateResponseEntity(HttpStatus.OK, "Failed to update course: " + e.getMessage());
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
