package com.example.demo.model.dto;

public class NewCourseDTO {
  private Integer id;
  private String title;
  private String description;
  private Integer mentorId;

  public NewCourseDTO() {
  }

  public NewCourseDTO(Integer id, String title, String description, Integer mentorId) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.mentorId = mentorId;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getMentorId() {
    return mentorId;
  }

  public void setMentorId(Integer mentorId) {
    this.mentorId = mentorId;
  }

  
  
}
