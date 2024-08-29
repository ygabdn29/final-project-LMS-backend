package com.example.demo.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Assignment;
import com.example.demo.repository.AssignmentRepository;
import com.example.demo.service.AssignmentService;

@Service
public class AssignmentServiceImpl implements AssignmentService {
  @Autowired
  private AssignmentRepository assignmentRepository;

  @Override
  public List<Assignment> get() {
    return assignmentRepository.findAll();
  }

  @Override
  public Assignment get(Integer id) {
    return assignmentRepository.findById(id).orElse(null);
  }

  @Override
  public Boolean save(Assignment entity) {
    assignmentRepository.save(entity);
    return assignmentRepository.findById(entity.getId()).isPresent();
  }

  @Override
  public Boolean delete(Integer id) {
    assignmentRepository.deleteById(id);
    return assignmentRepository.findById(id).isEmpty();
  }

  @Override
  public List<Assignment> getMaterialAssignments(Integer materialId) {
    List<Assignment> materialAssignment = assignmentRepository.findByMaterialId(materialId);
    return materialAssignment;
  }
}
