package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.handler.Utils;
import com.example.demo.model.Assignment;
import com.example.demo.model.AssignmentSubmission;
import com.example.demo.model.Course;
import com.example.demo.model.Material;
import com.example.demo.model.Progress;
import com.example.demo.model.User;
import com.example.demo.service.AssignmentService;
import com.example.demo.service.AssignmentSubmissionService;
import com.example.demo.service.CourseService;
import com.example.demo.service.MaterialService;
import com.example.demo.service.ProgressService;
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

  @Autowired
  private ProgressService progressService;

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

  @PostMapping("/{courseId}/{menteeId}")
  public ResponseEntity<Object> calculateProgress(@PathVariable Integer menteeId, @PathVariable Integer courseId) {
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
        return Utils.generateResponseEntity(HttpStatus.NOT_FOUND,
            "Material not found or does not belong to the specified course");
      }

      Assignment assignment = assignmentService.get(assignmentId);
      if (assignment == null || !assignment.getMaterial().getId().equals(materialId)) {
        return Utils.generateResponseEntity(HttpStatus.NOT_FOUND,
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
      submission.setIsPassed(false);

      assignmentSubmissionService.save(submission);

      return Utils.generateResponseEntity(HttpStatus.OK, "Assignment submitted successfully");
    } catch (Exception e) {
      return Utils.generateResponseEntity(HttpStatus.OK, "Failed to submit assignment: " + e.getMessage());
    }
  }

}
