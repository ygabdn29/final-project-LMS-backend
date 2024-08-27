package com.example.demo.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService{
  @Autowired
  private CourseRepository courseRepository;

  @Override
  public List<Course> get() {
    return courseRepository.findAll();
  }

  @Override
  public Course get(Integer id) {
    return courseRepository.findById(id).orElse(null);
  }

  @Override
  public Boolean save(Course entity) {
    courseRepository.save(entity);
    return courseRepository.findById(entity.getId()).isPresent();
  }

  @Override
  public Boolean delete(Integer id) {
    courseRepository.deleteById(id);
    return courseRepository.findById(id).isEmpty();
  }
  
}
