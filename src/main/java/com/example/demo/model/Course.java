package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_m_course")
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Integer id;

  @Column
  private String name;

  @Column(name = "begin_course")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate beginCourse;

  @Column(name = "end_course")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate endCourse;

  @OneToMany(mappedBy = "course")
  @JsonIgnore
  List<Material> materials;

  public Course() {

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

  public LocalDate getBeginCourse() {
    return beginCourse;
  }

  public void setBeginCourse(LocalDate beginCourse) {
    this.beginCourse = beginCourse;
  }

  public LocalDate getEndCourse() {
    return endCourse;
  }

  public void setEndCourse(LocalDate endCourse) {
    this.endCourse = endCourse;
  }

  public List<Material> getMaterials() {
    return materials;
  }

  public void setMaterials(List<Material> materials) {
    this.materials = materials;
  }

  
}
