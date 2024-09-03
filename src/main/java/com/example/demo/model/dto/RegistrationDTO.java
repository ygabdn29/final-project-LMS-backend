package com.example.demo.model.dto;

import java.time.LocalDate;

public class RegistrationDTO {
  private String firstName;
  private String lastName;
  private String email;
  private String address;
  private Boolean gender;
  private String phone;
  private String password;
  private Integer department_id;
  private LocalDate birthDate;

  public RegistrationDTO(){}

  public RegistrationDTO(String firstName, String lastName, String email, String address,
      Boolean gender, String phone, String password, Integer department_id, LocalDate birthDate) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.address = address;
    this.gender = gender;
    this.phone = phone;
    this.password = password;
    this.department_id = department_id;
    this.birthDate = birthDate;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Boolean getGender() {
    return gender;
  }

  public void setGender(Boolean gender) {
    this.gender = gender;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getDepartment_id() {
    return department_id;
  }

  public void setDepartment_id(Integer department_id) {
    this.department_id = department_id;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }
  
  
}
