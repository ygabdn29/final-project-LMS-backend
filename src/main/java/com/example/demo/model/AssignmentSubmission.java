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

  // manytoone
  @ManyToOne
  @JoinColumn(name = "course_transaction_id", referencedColumnName = "id")
  private CourseTransaction courseTransaction;

  @ManyToOne
  @JoinColumn(name = "assignment_id", referencedColumnName = "id")
  private Assignment assignment;


  public AssignmentSubmission() {
  }

  public AssignmentSubmission(Integer id, String answer, Float score, 
      Assignment assignment) {
    this.id = id;
    this.answer = answer;
    this.score = score;
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

  public CourseTransaction getCourseTransaction() {
    return courseTransaction;
  }

  public void setCourseTransaction(CourseTransaction courseTransaction) {
    this.courseTransaction = courseTransaction;
  }

  
}
