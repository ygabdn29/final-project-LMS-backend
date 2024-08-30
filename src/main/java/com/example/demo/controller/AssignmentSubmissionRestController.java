package com.example.demo.controller;

import com.example.demo.handler.Utils;
import com.example.demo.model.AssignmentSubmission;
import com.example.demo.service.AssignmentSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("api")
public class AssignmentSubmissionRestController {

  @Autowired
  private AssignmentSubmissionService assignmentSubmissionService;

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
  
  @PostMapping("/{courseId}/material/{materialId}/assignment/{assignmentId}/{submissionId}/grading")
  public ResponseEntity<Object> gradeSubmission(@PathVariable Integer submissionId, @RequestHeader Float score) {
     AssignmentSubmission assignmentSubmission = assignmentSubmissionService.get(submissionId);
      if (assignmentSubmission == null) {
        return Utils.generateResponseEntity(HttpStatus.OK, "Submission not found");
      }    
      if ((score > 100) || (score < 0)) {
        return Utils.generateResponseEntity(HttpStatus.OK, "Can't input score below 0 and above 100");
      }
      assignmentSubmission.setScore(score);
      assignmentSubmissionService.save(assignmentSubmission);
      return Utils.generateResponseEntity(HttpStatus.OK, "Submission successfully graded");
    } catch (Exception e) {
      return Utils.generateResponseEntity(HttpStatus.OK, "Failed to grade submission: " + e.getMessage());
    }
  }
}
