package com.example.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_m_role")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Integer id;

  @Column
  private String name;

  @Column
  private Integer level;

  @OneToMany(mappedBy = "role")
  @JsonIgnore
  private List<CourseUserRole> courseUserRoles;

  public Role() {
  }

  public Role(Integer id, String name, Integer level) {
    this.id = id;
    this.name = name;
    this.level = level;
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

  public List<CourseUserRole> getCourseUserRoles() {
    return courseUserRoles;
  }

  public void setCourseUserRoles(List<CourseUserRole> courseUserRoles) {
    this.courseUserRoles = courseUserRoles;
  }

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  

}
