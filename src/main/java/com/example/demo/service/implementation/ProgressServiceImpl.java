package com.example.demo.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Progress;
import com.example.demo.repository.ProgressRepository;
import com.example.demo.service.ProgressService;

@Service
public class ProgressServiceImpl implements ProgressService {
  @Autowired
  private ProgressRepository progressRepository;

  @Override
  public List<Progress> get() {
    return progressRepository.findAll();
  }

  @Override
  public Progress get(Integer id) {
    return progressRepository.findById(id).orElse(null);
  }

  @Override
  public Boolean save(Progress entity) {
    progressRepository.save(entity);
    return progressRepository.findById(entity.getId()).isPresent();
  }

  @Override
  public Boolean delete(Integer id) {
    progressRepository.deleteById(id);
    return progressRepository.findById(id).isEmpty();
  }
  
}
