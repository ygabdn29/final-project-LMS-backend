package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.handler.Utils;
import com.example.demo.model.Course;
import com.example.demo.model.CourseTransaction;
import com.example.demo.model.User;
import com.example.demo.model.dto.EnrollCourseDTO;
import com.example.demo.model.dto.NewCourseDTO;
import com.example.demo.service.CourseService;
import com.example.demo.service.CourseTransactionService;
import com.example.demo.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/course")
public class CourseRestController {
  @Autowired
  private CourseService courseService;

  @Autowired
  private UserService userService;

  @Autowired 
  private CourseTransactionService courseTransactionService;

  @PostMapping("create")
  public ResponseEntity<Object> newCourse(@RequestBody NewCourseDTO newCourseDTO) {
    try {
      User mentor = userService.get(newCourseDTO.getMentorId());
      if (mentor == null) {
        return Utils.generateResponseEntity(HttpStatus.OK, "User not found");
      }
      Course course = new Course(null, newCourseDTO.getTitle(), newCourseDTO.getDescription(), mentor);
      courseService.save(course);
      return Utils.generateResponseEntity(HttpStatus.OK, "Course successfully created");
    } catch (Exception e) {
      return Utils.generateResponseEntity(HttpStatus.OK, "Failed to create course: " + e.getMessage());
    }
  }
  
  @GetMapping("{courseId}")
  public ResponseEntity<Object> accessCourse(@PathVariable Integer courseId) {
    try {
      Course course = courseService.get(courseId);
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
    try {
      User newMentor = userService.get(newCourseDTO.getMentorId());
      Course course = courseService.get(newCourseDTO.getId());
      if (course == null) {
        return Utils.generateResponseEntity(HttpStatus.OK, "Course not found");
      }
      if (newMentor == null) {
        return Utils.generateResponseEntity(HttpStatus.OK, "User not found");
      }
      Course updatedCourse = new Course(newCourseDTO.getId(), newCourseDTO.getTitle(), newCourseDTO.getDescription(), newMentor);
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

  @GetMapping
  public ResponseEntity<Object> accessAllCourse() {
    try {
      List<Course> courses = courseService.get();
      return Utils.generateResponseEntity(HttpStatus.OK, "Courses accessed successfully", courses);
    } catch (Exception e) {
      return Utils.generateResponseEntity(HttpStatus.OK, "Failed to access course: " + e.getMessage());
    }
  }

  @PostMapping("/enroll")
  public ResponseEntity<Object> enrollCourse(@RequestBody EnrollCourseDTO enrollCourseDTO){
    Course course = courseService.get(enrollCourseDTO.getCourseId());
    User user = userService.get(enrollCourseDTO.getUserId());
    if(course == null) return Utils.generateResponseEntity(HttpStatus.OK, "Invalid Course");
    try {
      CourseTransaction courseTransaction = new CourseTransaction(null, user, course);
      courseTransactionService.save(courseTransaction);

      return Utils.generateResponseEntity(HttpStatus.OK, "Success enrolling course");
    } catch (Exception e) {
      return Utils.generateResponseEntity(HttpStatus.OK, "Error when enrolling course");
    }
  }
}
