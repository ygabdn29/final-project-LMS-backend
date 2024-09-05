package com.example.demo.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CourseTransaction;
import com.example.demo.repository.CourseTransactionRepository;
import com.example.demo.service.CourseTransactionService;

@Service
public class CourseTransactionServiceimpl implements CourseTransactionService{
  @Autowired
  private CourseTransactionRepository courseTransactionRepository;

  @Override
  public List<CourseTransaction> get() {
    return courseTransactionRepository.findAll();
  }

  @Override
  public CourseTransaction get(Integer id) {
    return courseTransactionRepository.findById(id).orElse(null);
  }

  @Override
  public Boolean save(CourseTransaction entity) {
    courseTransactionRepository.save(entity);
    return courseTransactionRepository.findById(entity.getId()).isPresent();
  }

  @Override
  public Boolean delete(Integer id) {
    courseTransactionRepository.deleteById(id);
    return courseTransactionRepository.findById(id).isEmpty();
  }

  @Override
  public CourseTransaction findByUserId(Integer userId) {
    return courseTransactionRepository.findByUserId(userId);
  }
}
