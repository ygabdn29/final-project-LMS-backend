package com.example.demo.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Material;
import com.example.demo.repository.MaterialRepository;
import com.example.demo.service.MaterialService;

@Service
public class MaterialServiceImpl implements MaterialService{
  @Autowired
  private MaterialRepository materialRepository;

  @Override
  public List<Material> get() {
    return materialRepository.findAll();
  }

  @Override
  public Material get(Integer id) {
    return materialRepository.findById(id).orElse(null);
  }

  @Override
  public Boolean save(Material entity) {
    materialRepository.save(entity);
    return materialRepository.findById(entity.getId()).isPresent();
  }

  @Override
  public Boolean delete(Integer id) {
    materialRepository.deleteById(id);
    return materialRepository.findById(id).isEmpty();
  }

  @Override
  public List<Material> getMaterial(Integer courseId) {
    List<Material> material = materialRepository.findByCourseId(courseId);
    return material;
  }
  
}
