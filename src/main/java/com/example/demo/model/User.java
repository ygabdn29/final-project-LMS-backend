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
  @Column
  private String username;

  @Column
  private String password;

  @Column(name = "is_admin")
  private Boolean isAdmin;

  @Id
  @Column
  private Integer id;

  @OneToOne
  @MapsId
  @JsonIgnore
  private Employee employee;

  @OneToMany(mappedBy = "user")
  @JsonIgnore
  private List<Progress> progresses;

  @OneToMany(mappedBy = "user")
  @JsonIgnore
  private List<AssignmentSubmission> assignmentSubmissions;

  public User() {
  }

  public User(String username, String password, Boolean isAdmin, Integer id, Employee employee) {
    this.username = username;
    this.password = password;
    this.isAdmin = isAdmin;
    this.id = id;
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

  public Boolean getIsAdmin() {
    return isAdmin;
  }

  public void setIsAdmin(Boolean isAdmin) {
    this.isAdmin = isAdmin;
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

  public List<Progress> getProgresses() {
    return progresses;
  }

  public void setProgresses(List<Progress> progresses) {
    this.progresses = progresses;
  }

  public List<AssignmentSubmission> getAssignmentSubmissions() {
    return assignmentSubmissions;
  }

  public void setAssignmentSubmissions(List<AssignmentSubmission> assignmentSubmissions) {
    this.assignmentSubmissions = assignmentSubmissions;
  } 

}
