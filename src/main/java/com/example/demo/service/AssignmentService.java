package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Assignment;
import com.example.demo.service.generic.GenericService;

public interface AssignmentService extends GenericService<Assignment, Integer> {
  public List<Assignment> getMaterialAssignments(Integer materialId);
}
