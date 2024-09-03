package com.example.demo.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_m_employee")
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Integer id;

  @Column(name ="first_name")
  private String firstName;

  @Column(name ="last_name")
  private String lastName;

  @Column
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dob;

  @Column
  private Boolean gender;

  @Column(columnDefinition = "TEXT")
  private String address;

  @Column
  private String phone;

  @Column
  private String email;

  @ManyToOne
  @JoinColumn(name = "dept_id", referencedColumnName = "id")
  private Department department;

  @OneToOne(mappedBy = "employee")
  @JsonIgnore
  private User user;


  public Employee() {
  }

  public Employee(Integer id, String firstName, String lastName, LocalDate dob, Boolean gender,
      String address, String phone, String email, Department department) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dob = dob;
    this.gender = gender;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.department = department;
  }

  public Integer getId() {
    return id;
  }


  public void setId(Integer id) {
    this.id = id;
  }


  public String getFirstName() {
    return firstName;
  }


  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }


  public String getLastName() {
    return lastName;
  }


  public void setLastName(String lastName) {
    this.lastName = lastName;
  } 

  public Boolean getGender() {
    return gender;
  }


  public void setGender(Boolean gender) {
    this.gender = gender;
  }


  public String getAddress() {
    return address;
  }


  public void setAddress(String address) {
    this.address = address;
  }


  public String getPhone() {
    return phone;
  }


  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getEmail() {
    return email;
  }


  public void setEmail(String email) {
    this.email = email;
  }


  public Department getDepartment() {
    return department;
  }


  public void setDepartment(Department department) {
    this.department = department;
  }


  public User getUser() {
    return user;
  }


  public void setUser(User user) {
    this.user = user;
  }


  public LocalDate getDob() {
    return dob;
  }

  public void setDob(LocalDate dob) {
    this.dob = dob;
  }
 
  
}
