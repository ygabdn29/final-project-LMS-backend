package com.example.demo.controller;

import com.example.demo.handler.Utils;
import com.example.demo.model.AssignmentSubmission;
import com.example.demo.service.AssignmentSubmissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api")
public class AssignmentSubmissionRestController {

  @Autowired
  private AssignmentSubmissionService assignmentSubmissionService;


  @GetMapping("/{courseId}/material/{materialId}/assignment/{assignmentId}/{submissionId}/score")
  public ResponseEntity<Object> accessScore(@PathVariable Integer submissionId) {
    try {
      AssignmentSubmission assignmentSubmission = assignmentSubmissionService.get(submissionId);
      if (assignmentSubmission == null) {
        return Utils.generateResponseEntity(HttpStatus.OK, "Submission not found");
      }
      return Utils.generateResponseEntity(HttpStatus.OK, "Your score is: ", assignmentSubmission.getScore());
    } catch (Exception e) {
      return Utils.generateResponseEntity(HttpStatus.OK, "Failed to access score: " + e.getMessage());
    }
  }
}
