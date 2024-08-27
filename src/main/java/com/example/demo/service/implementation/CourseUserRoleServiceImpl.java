package com.example.demo.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CourseUserRole;
import com.example.demo.repository.CourseUserRoleRepository;
import com.example.demo.service.CourseUserRoleService;

@Service
public class CourseUserRoleServiceImpl implements CourseUserRoleService{
  @Autowired
  private CourseUserRoleRepository courseUserRoleRepository;

  @Override
  public List<CourseUserRole> get() {
    return courseUserRoleRepository.findAll();
  }

  @Override
  public CourseUserRole get(Integer id) {
    return courseUserRoleRepository.findById(id).orElse(null);
  }

  @Override
  public Boolean save(CourseUserRole entity) {
    courseUserRoleRepository.save(entity);
    return courseUserRoleRepository.findById(entity.getId()).isPresent();
  }

  @Override
  public Boolean delete(Integer id) {
    courseUserRoleRepository.deleteById(id);
    return courseUserRoleRepository.findById(id).isEmpty();
  }

  
  
}
