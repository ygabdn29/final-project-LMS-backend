package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Material;
import com.example.demo.service.generic.GenericService;

public interface MaterialService extends GenericService<Material, Integer> {
  public List<Material> getMaterial(Integer courseId);
}
