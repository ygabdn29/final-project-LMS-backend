package com.example.demo.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AssignmentSubmission;
import com.example.demo.repository.AssignmentSubmissionRepository;
import com.example.demo.service.AssignmentSubmissionService;

@Service
public class AssigmentSubmissionServiceImpl implements AssignmentSubmissionService{
  @Autowired
  private AssignmentSubmissionRepository assignmentSubmissionRepository;

  @Override
  public List<AssignmentSubmission> get() {
    return assignmentSubmissionRepository.findAll();
  }

  @Override
  public AssignmentSubmission get(Integer id) {
    return assignmentSubmissionRepository.findById(id).orElse(null);
  }

  @Override
  public Boolean save(AssignmentSubmission entity) {
    assignmentSubmissionRepository.save(entity);
    return assignmentSubmissionRepository.findById(entity.getId()).isPresent();
  }

  @Override
  public Boolean delete(Integer id) {
    assignmentSubmissionRepository.deleteById(id);
    return assignmentSubmissionRepository.findById(id).isEmpty();
  }

}