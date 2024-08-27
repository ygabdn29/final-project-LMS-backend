package com.example.demo.model.dto;

import java.time.LocalDate;

public class NewCourseDTO {
  private String name;
  private LocalDate begin;
  private LocalDate end;
  private Integer mentorId;

  public NewCourseDTO(String name, LocalDate begin, LocalDate end, Integer mentorId) {
    this.name = name;
    this.begin = begin;
    this.end = end;
    this.mentorId = mentorId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public Integer getMentorId() {
    return mentorId;
  }

  public void setMentorId(Integer mentorId) {
    this.mentorId = mentorId;
  }

  
  

}
