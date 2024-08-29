package com.example.demo.model;

import java.time.LocalDate;
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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_m_assignment")
public class Assignment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Integer id;

  @Column
  private String name;

  @Column(columnDefinition = "TEXT")
  private String content;

  @Column(name = "passing_score")
  private Float passingScore;

  @Column(name = "due_date")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dueDate;

  @ManyToOne
  @JoinColumn(name = "material_id", referencedColumnName = "id")
  private Material material;
  
  @OneToMany(mappedBy = "assignment")
  @JsonIgnore
  private List<AssignmentSubmission> assignmentSubmissions;


  public Assignment() {
  }

  public Assignment(Integer id, String name, String content, Float passingScore, LocalDate dueDate, Material material) {
    this.id = id;
    this.name = name;
    this.content = content;
    this.passingScore = passingScore;
    this.dueDate = dueDate;
    this.material = material;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Float getPassingScore() {
    return passingScore;
  }

  public void setPassingScore(Float passingScore) {
    this.passingScore = passingScore;
  }

  public LocalDate getDueDate() {
    return dueDate;
  }

  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
  }

  public Material getMaterial() {
    return material;
  }

  public void setMaterial(Material material) {
    this.material = material;
  }

  public List<AssignmentSubmission> getAssignmentSubmissions() {
    return assignmentSubmissions;
  }

  public void setAssignmentSubmissions(List<AssignmentSubmission> assignmentSubmissions) {
    this.assignmentSubmissions = assignmentSubmissions;
  }
}
