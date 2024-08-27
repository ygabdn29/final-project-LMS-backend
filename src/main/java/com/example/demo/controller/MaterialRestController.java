package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Course;
import com.example.demo.model.Material;
import com.example.demo.service.CourseService;
import com.example.demo.service.MaterialService;

import com.example.demo.handler.Utils;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/course")
public class MaterialRestController {
  @Autowired
  private MaterialService materialService;

  @Autowired
  private CourseService courseService;

  @PostMapping("/{id}/material")
  public ResponseEntity<Object> Material(@PathVariable Integer id, @RequestBody Material material) {
    try {
      Course course = courseService.get(id);

      if (course == null) {
        return Utils.generateResponseEntity(HttpStatus.OK, "Course not found");
      }

      material.setCourse(course);

      materialService.save(material);

      return Utils.generateResponseEntity(HttpStatus.OK, "Add Material Successfully");
    } catch (Exception e) {
      return Utils.generateResponseEntity(HttpStatus.OK, "Failed add material" + e.getMessage());
    }
  }
}
