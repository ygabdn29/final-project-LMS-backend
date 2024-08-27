package com.example.demo.model.dto;

import java.time.LocalDate;

public class NewCourseDTO {
  private String name;
  private LocalDate begin;
  private LocalDate end;
  private String description;
  private Integer quota;
  private Integer mentorId;

  public NewCourseDTO() {
  }

  public NewCourseDTO(String name, LocalDate begin, LocalDate end, String description, Integer quota,
      Integer mentorId) {
    this.name = name;
    this.begin = begin;
    this.end = end;
    this.description = description;
    this.quota = quota;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getQuota() {
    return quota;
  }

  public void setQuota(Integer quota) {
    this.quota = quota;
  }

  public Integer getMentorId() {
    return mentorId;
  }

  public void setMentorId(Integer mentorId) {
    this.mentorId = mentorId;
  }

  
  
}
