package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_tr_assignment_submission")
public class AssignmentSubmission {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Integer id;

  @Column(columnDefinition = "TEXT")
  private String answer;

  @Column
  private Float score;

  @Column(name = "is_passed")
  private Boolean isPassed;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "employee_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "assignment_id", referencedColumnName = "id")
  private Assignment assignment;

  public AssignmentSubmission() {
  }

  public AssignmentSubmission(Integer id, String answer, Float score, Boolean isPassed, User user,
      Assignment assignment) {
    this.id = id;
    this.answer = answer;
    this.score = score;
    this.isPassed = isPassed;
    this.user = user;
    this.assignment = assignment;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }  

  public Boolean getIsPassed() {
    return isPassed;
  }

  public void setIsPassed(Boolean isPassed) {
    this.isPassed = isPassed;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Assignment getAssignment() {
    return assignment;
  }

  public void setAssignment(Assignment assignment) {
    this.assignment = assignment;
  }

  public Float getScore() {
    return score;
  }

  public void setScore(Float score) {
    this.score = score;
  }

}
