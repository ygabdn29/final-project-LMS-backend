package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.handler.Utils;
import com.example.demo.model.Assignment;
import com.example.demo.model.AssignmentSubmission;
import com.example.demo.model.Course;
import com.example.demo.model.Material;
import com.example.demo.model.User;
import com.example.demo.service.AssignmentService;
import com.example.demo.service.AssignmentSubmissionService;
import com.example.demo.service.CourseService;
import com.example.demo.service.MaterialService;
import com.example.demo.service.UserService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/course")
public class AssignmentSubmissionRestController {
  @Autowired
  private MaterialService materialService;

  @Autowired
  private AssignmentSubmissionService assignmentSubmissionService;

  @Autowired
  private CourseService courseService;

  @Autowired
  private UserService userService;

  @Autowired
  private AssignmentService assignmentService;

  @PostMapping("/{courseId}/material/{materialId}/assignment/{assignmentId}/submit")
  public ResponseEntity<Object> submitAssignment(
      @PathVariable Integer courseId,
      @PathVariable Integer materialId,
      @PathVariable Integer assignmentId,
      @RequestBody Map<String, String> request,
      @RequestHeader Integer userId) {
    try {
      Course course = courseService.get(courseId);
      if (course == null) {
        return Utils.generateResponseEntity(HttpStatus.OK, "Course not found");
      }

      Material material = materialService.get(materialId);
      if (material == null || !material.getCourse().getId().equals(courseId)) {
        return Utils.generateResponseEntity(HttpStatus.OK,
            "Material not found or does not belong to the specified course");
      }

      Assignment assignment = assignmentService.get(assignmentId);
      if (assignment == null || !assignment.getMaterial().getId().equals(materialId)) {
        return Utils.generateResponseEntity(HttpStatus.OK,
            "Assignment not found or does not belong to the specified material");
      }

      User user = userService.get(userId);
      if (user == null) {
        return Utils.generateResponseEntity(HttpStatus.OK, "User not found");
      }

      String answer = request.get("answer");
      AssignmentSubmission submission = new AssignmentSubmission();
      submission.setAssignment(assignment);
      submission.setAnswer(answer);
      submission.setUser(user);

      assignmentSubmissionService.save(submission);

      return Utils.generateResponseEntity(HttpStatus.OK, "Assignment submitted successfully");
    } catch (Exception e) {
      return Utils.generateResponseEntity(HttpStatus.OK, "Failed to submit assignment: " + e.getMessage());
    }
  }

}
