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
  private Integer passingScore;

  @Column(name = "due_date")
  private Integer dueDate;

  @ManyToOne
  @JoinColumn(name = "material_id", referencedColumnName = "id")
  private Material material;
  
  @OneToMany(mappedBy = "assignment")
  @JsonIgnore
  private List<AssignmentSubmission> assignments;

  public Assignment() {
  }  

  public Assignment(Integer id, String name, String content, Integer passingScore, Integer dueDate, Material material) {
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

  public Integer getPassingScore() {
    return passingScore;
  }

  public void setPassingScore(Integer passingScore) {
    this.passingScore = passingScore;
  }

  public Integer getDueDate() {
    return dueDate;
  }

  public void setDueDate(Integer dueDate) {
    this.dueDate = dueDate;
  }

  public Material getMaterial() {
    return material;
  }

  public void setMaterial(Material material) {
    this.material = material;
  }

  public List<AssignmentSubmission> getAssignments() {
    return assignments;
  }

  public void setAssignments(List<AssignmentSubmission> assignments) {
    this.assignments = assignments;
  }

  
}
