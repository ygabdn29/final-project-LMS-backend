package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.handler.Utils;
import com.example.demo.model.AssignmentSubmission;
import com.example.demo.service.AssignmentSubmissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("api/submission")
public class AssignmentSubmissionRestController {
  @Autowired
  private AssignmentSubmissionService assignmentSubmissionService;

  @GetMapping("grade")
  public ResponseEntity<Object> accessGrade(@RequestHeader Integer id) {
    try {
      AssignmentSubmission assignmentSubmission = assignmentSubmissionService.get(id);
      if(assignmentSubmission == null){
        return Utils.generateResponseEntity(HttpStatus.OK, "Submission not found");  
      }      
      return Utils.generateResponseEntity(HttpStatus.OK, "Your grade is: ", assignmentSubmission.getGrade());
    } catch (Exception e) {
      return Utils.generateResponseEntity(HttpStatus.OK, "Failed to access grade: " + e.getMessage());
    }
  }

}
