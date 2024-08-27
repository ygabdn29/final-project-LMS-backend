package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_m_assignment_key")
public class AssignmentKey {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Integer id;

  @Column(columnDefinition = "TEXT")
  private String key;

  @OneToOne
  @JoinColumn(name = "assignment_id", referencedColumnName = "id")
  private Assignment assignment;

  public AssignmentKey() {
  }

  public AssignmentKey(Integer id, String key, Assignment assignment) {
    this.id = id;
    this.key = key;
    this.assignment = assignment;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public Assignment getAssignment() {
    return assignment;
  }

  public void setAssignment(Assignment assignment) {
    this.assignment = assignment;
  }

  
}
