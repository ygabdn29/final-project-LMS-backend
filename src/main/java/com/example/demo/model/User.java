package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_m_user")
public class User {
  @Column
  private String username;

  @Column
  private String password;

  @Column(name = "is_active")
  private Boolean isActive;

  @Column(name = "guid")
  private String guid;

  @Column
  private LocalDate startSubs;

  @Column
  private LocalDate endSubs;

  @Id
  @Column
  private Integer id;

  @OneToOne
  @MapsId
  @JsonIgnore
  private Employee employee;

  @OneToMany(mappedBy = "user")
  @JsonIgnore
  private List<CourseTransaction> courseTransactions;

  @OneToMany(mappedBy = "user")
  @JsonIgnore
  private List<UserRole> userRoles;

  public User() {
  }

  public User(String username, String password, Integer id, Employee employee, Boolean isActive) {
    this.username = username;
    this.password = password;
    this.id = id;
    this.employee = employee;
    this.isActive = isActive;
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

  public String getGuid() {
    return guid;
  }

  public void setGuid(String guid) {
    this.guid = guid;
  }

  public List<CourseTransaction> getCourseTransactions() {
    return courseTransactions;
  }

  public void setCourseTransactions(List<CourseTransaction> courseTransactions) {
    this.courseTransactions = courseTransactions;
  }

  public Boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }

  public LocalDate getStartSubs() {
    return startSubs;
  }

  public void setStartSubs(LocalDate startSubs) {
    this.startSubs = startSubs;
  }

  public LocalDate getEndSubs() {
    return endSubs;
  }

  public void setEndSubs(LocalDate endSubs) {
    this.endSubs = endSubs;
  }

  public List<UserRole> getUserRoles() {
    return userRoles;
  }

  public void setUserRoles(List<UserRole> userRoles) {
    this.userRoles = userRoles;
  }

  
}
