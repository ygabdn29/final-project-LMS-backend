package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.handler.Utils;
import com.example.demo.model.Assignment;
import com.example.demo.model.AssignmentSubmission;
import com.example.demo.model.Progress;
import com.example.demo.service.AssignmentService;
import com.example.demo.service.AssignmentSubmissionService;
import com.example.demo.service.ProgressService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("api/submission")
public class AssignmentSubmissionRestController {
  @Autowired
  private AssignmentSubmissionService assignmentSubmissionService;

  @Autowired
  private AssignmentService assignmentService;

  @Autowired
  private ProgressService progressService;

  @GetMapping("grade")
  public ResponseEntity<Object> accessGrade(@RequestHeader Integer id) {
    try {
      AssignmentSubmission assignmentSubmission = assignmentSubmissionService.get(id);
      if (assignmentSubmission == null) {
        return Utils.generateResponseEntity(HttpStatus.OK, "Submission not found");
      }
      return Utils.generateResponseEntity(HttpStatus.OK, "Your grade is: ", assignmentSubmission.getGrade());
    } catch (Exception e) {
      return Utils.generateResponseEntity(HttpStatus.OK, "Failed to access grade: " + e.getMessage());
    }
  }

  @GetMapping
  public ResponseEntity<Object> accessSubmission(@RequestHeader Integer id) {
    try {
      AssignmentSubmission assignmentSubmission = assignmentSubmissionService.get(id);
      if (assignmentSubmission == null) {
        return Utils.generateResponseEntity(HttpStatus.OK, "Submission not found");
      }
      return Utils.generateResponseEntity(HttpStatus.OK, "Submission successfully accessed: ", assignmentSubmission);
    } catch (Exception e) {
      return Utils.generateResponseEntity(HttpStatus.OK, "Failed to access submission: " + e.getMessage());
    }
  }

  @PostMapping("grading")
  public ResponseEntity<Object> gradeSubmission(@RequestHeader Integer id, @RequestHeader Integer grade) {
    try {
      AssignmentSubmission assignmentSubmission = assignmentSubmissionService.get(id);
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

  @PostMapping("progress")
  public ResponseEntity<Object> calculateProgress(@RequestHeader Integer menteeId, @RequestHeader Integer courseId) {
    try {
      List<Assignment> allAssignments = assignmentService.get().stream()
          .filter(assignment -> assignment.getMaterial().getCourse().getId().equals(courseId))
          .collect(Collectors.toList());

      if (allAssignments.isEmpty()) {
        return Utils.generateResponseEntity(HttpStatus.OK, "Course not found");
      }

      Integer totalAssignments = allAssignments.size();

      List<AssignmentSubmission> menteeSubmissions = assignmentSubmissionService.get().stream()
          .filter(submission -> submission.getUser().getId().equals(menteeId))
          .collect(Collectors.toList());

      if (menteeSubmissions.isEmpty()) {
        return Utils.generateResponseEntity(HttpStatus.OK, "Submissions not found");
      }

      Integer passedAssignments = 0; // counter isPassed
      Integer lastAssignment = null; // jump pointer for loop below

      for (AssignmentSubmission submission : menteeSubmissions) {
        if ((lastAssignment == null) || (submission.getAssignment().getId() != lastAssignment)) {
          if (submission.getIsPassed() == true) {
            passedAssignments += 1;
            lastAssignment = submission.getAssignment().getId();
          }
        }
      }

      Double progressPercent = Double.valueOf(passedAssignments) / Double.valueOf(totalAssignments) * 100;
      Integer progressRounded = (int) Math.round(progressPercent);

      Progress progress = progressService.get(menteeId);
      progress.setProgress(progressRounded);
      progressService.save(progress);

      return Utils.generateResponseEntity(HttpStatus.OK, "Progress successfully updated");
    } catch (Exception e) {
      return Utils.generateResponseEntity(HttpStatus.OK, "Failed to update progress: " + e.getMessage());
    }
  }

}
