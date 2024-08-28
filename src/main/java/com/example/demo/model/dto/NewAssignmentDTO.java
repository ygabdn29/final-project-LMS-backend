package com.example.demo.model.dto;

import java.time.LocalDate;

public class NewAssignmentDTO {
  private String content;
  private LocalDate dueDate;
  private String name;
  private Integer passingScore;
  private Integer materialId;

  public NewAssignmentDTO(){}

  public NewAssignmentDTO(String content, LocalDate dueDate, String name, Integer passingScore, Integer materialId) {
    this.content = content;
    this.dueDate = dueDate;
    this.name = name;
    this.passingScore = passingScore;
    this.materialId = materialId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public LocalDate getDueDate() {
    return dueDate;
  }

  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getPassingScore() {
    return passingScore;
  }

  public void setPassing_score(Integer passingScore) {
    this.passingScore = passingScore;
  }

  public Integer getMaterialId() {
    return materialId;
  }

  public void setMaterialId(Integer materialId) {
    this.materialId = materialId;
  }

  
}
