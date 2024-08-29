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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api")
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

  @GetMapping("/{courseId}/material/{materialId}/assignment/{assignmentId}/{submissionId}/grade")
  public ResponseEntity<Object> accessGrade(@PathVariable Integer submissionId) {
    try {
      AssignmentSubmission assignmentSubmission = assignmentSubmissionService.get(submissionId);
      if (assignmentSubmission == null) {
        return Utils.generateResponseEntity(HttpStatus.OK, "Submission not found");
      }
      return Utils.generateResponseEntity(HttpStatus.OK, "Your grade is: ", assignmentSubmission.getGrade());
    } catch (Exception e) {
      return Utils.generateResponseEntity(HttpStatus.OK, "Failed to access grade: " + e.getMessage());
    }
  }

  @GetMapping("/{courseId}/material/{materialId}/assignment/{assignmentId}/{submissionId}")
  public ResponseEntity<Object> accessSubmission(@PathVariable Integer submissionId) {
    try {
      AssignmentSubmission assignmentSubmission = assignmentSubmissionService.get(submissionId);
      if (assignmentSubmission == null) {
        return Utils.generateResponseEntity(HttpStatus.OK, "Submission not found");
      }
      return Utils.generateResponseEntity(HttpStatus.OK, "Submission successfully accessed: ", assignmentSubmission);
    } catch (Exception e) {
      return Utils.generateResponseEntity(HttpStatus.OK, "Failed to access submission: " + e.getMessage());
    }
  }

  @PostMapping("/{courseId}/material/{materialId}/assignment/{assignmentId}/{submissionId}/grading")
  public ResponseEntity<Object> gradeSubmission(@PathVariable Integer submissionId, @RequestHeader Integer grade) {
    try {
      AssignmentSubmission assignmentSubmission = assignmentSubmissionService.get(submissionId);
      if (assignmentSubmission == null) {
        return Utils.generateResponseEntity(HttpStatus.OK, "Submission not found");
      }
      Integer passingGrade = assignmentSubmission.getAssignment().getPassingScore();
      assignmentSubmission.setGrade(grade);
      if (assignmentSubmission.getGrade() >= passingGrade) {
        assignmentSubmission.setIsPassed(true);
      } else {
        assignmentSubmission.setIsPassed(false);
      }
      assignmentSubmissionService.save(assignmentSubmission);
      return Utils.generateResponseEntity(HttpStatus.OK, "Submission successfully graded");
    } catch (Exception e) {
      return Utils.generateResponseEntity(HttpStatus.OK, "Failed to grade submission: " + e.getMessage());
    }
  }

   

}
