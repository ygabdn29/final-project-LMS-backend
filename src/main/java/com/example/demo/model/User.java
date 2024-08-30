package com.example.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

  @Column(name ="is_verified")
  private Boolean isVerified;

  @Column(name="guid")
  private String guid;

  @Id
  @Column
  private Integer id;

  @OneToOne
  @MapsId
  @JsonIgnore
  private Employee employee;

  @OneToOne
  @JoinColumn(name = "role_id", referencedColumnName = "id")
  private Role role;

  @OneToMany(mappedBy = "user")
  @JsonIgnore
  private List<AssignmentSubmission> assignmentSubmissions;

  @OneToOne
  @JoinColumn(name = "role_id", referencedColumnName = "id")
  private Role role;

  public User() {
  }

  public User(String username, String password, Integer id, Employee employee, Role role, Boolean isVerified) {
    this.username = username;
    this.password = password;
    this.id = id;
    this.employee = employee;
    this.role = role;
    this.isVerified = isVerified;
  }

  public void setRole(Role role) {
    this.role = role;
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

  public List<AssignmentSubmission> getAssignmentSubmissions() {
    return assignmentSubmissions;
  }

  public void setAssignmentSubmissions(List<AssignmentSubmission> assignmentSubmissions) {
    this.assignmentSubmissions = assignmentSubmissions;
  }

  public String getGuid() {
    return guid;
  }

  public void setGuid(String guid) {
    this.guid = guid;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public Boolean getIsVerified() {
    return isVerified;
  }

  public void setIsVerified(Boolean isVerified) {
    this.isVerified = isVerified;
  } 
}
