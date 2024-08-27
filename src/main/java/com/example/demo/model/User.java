package com.example.demo.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_m_user")
public class User {

  @Column(name ="username")
  private String username;

  @Column(name ="password")
  private String password;

  @Id
  @Column
  private Integer id;

  @OneToOne
  @MapsId
  @JsonIgnore
  private Employee employee;

  @OneToMany(mappedBy = "user")
  @JsonIgnore
  private List<CourseUserRole> courseUserRoles;


  public User() {
  }

  public User(String username, String password, Employee employee) {
    this.username = username;
    this.password = password;
    this.employee = employee;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public List<CourseUserRole> getCourseUserRoles() {
    return courseUserRoles;
  }

  public void setCourseUserRoles(List<CourseUserRole> courseUserRoles) {
    this.courseUserRoles = courseUserRoles;
  }

  
  
}
