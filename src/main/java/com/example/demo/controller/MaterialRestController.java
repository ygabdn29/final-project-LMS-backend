package com.example.demo.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("api/course")
public class MaterialRestController {
  @Autowired
  private MaterialService materialService;

  @Autowired
  private CourseService courseService;

  @GetMapping("/{id}/materials")
  public ResponseEntity<Object> get(@PathVariable Integer id) {
    try {
      Course course = courseService.get(id);
      if (course == null) {
        return Utils.generateResponseEntity(HttpStatus.OK, "Course not found");
      }

      List<Material> materials = materialService.get();

      return Utils.generateResponseEntity(HttpStatus.OK, "Data Materials Has Been Retrieved", materials);
    } catch (Exception e) {
      return Utils.generateResponseEntity(HttpStatus.OK, "Failed to Fetch Data Materials" + e.getMessage());
    }
  }

  @GetMapping("/{courseId}/material/{materialId}")
  public ResponseEntity<Object> getById(@PathVariable Integer courseId, @PathVariable Integer materialId) {
    try {

      Course course = courseService.get(courseId);
      if (course == null) {
        return Utils.generateResponseEntity(HttpStatus.OK, "Course not found");
      }

      Material material = materialService.get(materialId);
      if (material == null || !material.getCourse().getId().equals(courseId)){
        return Utils.generateResponseEntity(HttpStatus.OK, "Material not found or does not belong to the specified course");
      }

      return Utils.generateResponseEntity(HttpStatus.OK, "Material Has Been Retrieved");
    } catch (Exception e) {
      return Utils.generateResponseEntity(HttpStatus.OK, "Failed to Fetch Data Materials" + e.getMessage());
    }
  }

  @PostMapping("/{id}/new-material")
  public ResponseEntity<Object> addMaterial(@PathVariable Integer id, @RequestBody Material material) {
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

  @PutMapping("/{courseId}/material/{materialId}")
  public ResponseEntity<Object> updateMaterial(@PathVariable Integer courseId, @PathVariable Integer materialId,
      @RequestBody Material material) {
    try {
      Course course = courseService.get(courseId);
      if (course == null) {
        return Utils.generateResponseEntity(HttpStatus.OK, "Course not found");
      }

      Material selectMaterial = materialService.get(materialId);
      if (selectMaterial == null) {
        return Utils.generateResponseEntity(HttpStatus.OK, "Material not found");
      }

      if(!selectMaterial.getCourse().getId().equals(courseId)){
        return Utils.generateResponseEntity(HttpStatus.OK, "Material does not belong to this course");
      }

      selectMaterial.setTitle(material.getTitle());
      selectMaterial.setContent(material.getContent());

      materialService.save(selectMaterial);
      return Utils.generateResponseEntity(HttpStatus.OK, "Update Material Successfully");
    } catch (Exception e) {
      return Utils.generateResponseEntity(HttpStatus.OK, "Failed to update material" + e.getMessage());
    }
  }

  @DeleteMapping("/{courseId}/material/{materialId}")
  public ResponseEntity<Object> delete(@PathVariable Integer courseId, @PathVariable Integer materialId) {
    try {
      Course course = courseService.get(courseId);
      if (course == null) {
        return Utils.generateResponseEntity(HttpStatus.OK, "Course not found");
      }

      materialService.delete(materialId);
      return Utils.generateResponseEntity(HttpStatus.OK, "Material Has Been Deleted");
    } catch (Exception e) {
      return Utils.generateResponseEntity(HttpStatus.OK, "Failed to Delete Data Materials" + e.getMessage());
    }
  }
}
