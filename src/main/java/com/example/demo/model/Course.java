package com.example.demo.model;

import java.time.LocalDate;
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

  @Column
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate begin;

  @Column
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate end;

  @OneToMany(mappedBy = "course")
  @JsonIgnore
  private List<Material> materials;

  @OneToMany(mappedBy = "course")
  @JsonIgnore
  private List<Progress> progresses;
  
  @OneToOne
  @JoinColumn(name = "mentor_id", referencedColumnName = "employee_id")
  private User mentor;

  public Course() {
  }

  public Course(Integer id, String name, LocalDate begin, LocalDate end) {
    this.id = id;
    this.name = name;
    this.begin = begin;
    this.end = end;
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

  


}
