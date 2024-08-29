package com.example.demo.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "tb_tr_progress")
public class Progress {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Integer id;

  @Column
  private LocalDate begin;

  @Column
  private LocalDate end;

  @Column
  private Integer batch;

  @Column
  private Integer progress;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "employee_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "course_id", referencedColumnName = "id")
  private Course course;

  public Progress() {
  }

  public Progress(Integer id, LocalDate begin, LocalDate end, Integer batch, Integer progress, User user, Course course) {
    this.id = id;
    this.begin = begin;
    this.end = end;
    this.batch = batch;
    this.progress = progress;
    this.user = user;
    this.course = course;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public LocalDate getBegin() {
    return begin;
  }

  public void setBegin(LocalDate begin) {
    this.begin = begin;
  }

  public LocalDate getEnd() {
    return end;
  }

  public void setEnd(LocalDate end) {
    this.end = end;
  }

  public Integer getBatch() {
    return batch;
  }

  public void setBatch(Integer batch) {
    this.batch = batch;
  }

  public Integer getProgress() {
    return progress;
  }

  public void setProgress(Integer progress) {
    this.progress = progress;
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

  
  
}
