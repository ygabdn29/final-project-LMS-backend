package com.example.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_m_course")
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Integer id;

  @Column
  private String title;

  @Column
  private String description;

  @OneToMany(mappedBy = "course")
  @JsonIgnore
  private List<Material> materials;

  @OneToOne
  @JoinColumn(name = "mentor_id", referencedColumnName = "employee_id")
  private User mentor;

  public Course() {
  } 

  public Course(Integer id, String title, String description, User mentor) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.mentor = mentor;
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

  public List<Material> getMaterials() {
    return materials;
  }

  public void setMaterials(List<Material> materials) {
    this.materials = materials;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public User getMentor() {
    return mentor;
  }

  public void setMentor(User mentor) {
    this.mentor = mentor;
  }
    
}
