package com.example.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
@Entity
@Table(name ="tb_tr_course_transaction")
public class CourseTransaction implements Serializable{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "employee_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "course_id", referencedColumnName = "id")
  private Course course;

  @OneToMany(mappedBy = "courseTransaction")
  @JsonIgnore
  private List<AssignmentSubmission> assignmentSubmissions;

  public CourseTransaction(){

  }

  public CourseTransaction(Integer id, User user, Course course) {
    this.id = id;
    this.user = user;
    this.course = course;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public List<AssignmentSubmission> getAssignmentSubmissions() {
    return assignmentSubmissions;
  }

  public void setAssignmentSubmissions(List<AssignmentSubmission> assignmentSubmissions) {
    this.assignmentSubmissions = assignmentSubmissions;
  }

  
}
