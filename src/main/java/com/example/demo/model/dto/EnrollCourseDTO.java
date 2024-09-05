package com.example.demo.model.dto;

public class EnrollCourseDTO {
  private Integer userId;
  private Integer courseId;

  public EnrollCourseDTO(){}

  public EnrollCourseDTO(Integer userId, Integer courseId) {
    this.userId = userId;
    this.courseId = courseId;
  }
  public Integer getUserId() {
    return userId;
  }
  public void setUserId(Integer userId) {
    this.userId = userId;
  }
  public Integer getCourseId() {
    return courseId;
  }
  public void setCourseId(Integer courseId) {
    this.courseId = courseId;
  }
  
}
