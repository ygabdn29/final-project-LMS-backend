package com.example.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_m_material")
public class Material {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Integer id;

  @Column
  private String title;

  @Column(columnDefinition = "TEXT")
  private String content;

  @ManyToOne
  @JoinColumn(name = "course_id", referencedColumnName = "id")
  private Course course;

  @OneToMany(mappedBy = "material")
  @JsonIgnore
  private List<Assignment> assignments;
  

  public Material(){

  }  

  public Material(Integer id, String title, String content) {
    this.id = id;
    this.title = title;
    this.content = content;
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

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public List<Assignment> getAssignments() {
    return assignments;
  }

  public void setAssignments(List<Assignment> assignments) {
    this.assignments = assignments;
  }
  
}
