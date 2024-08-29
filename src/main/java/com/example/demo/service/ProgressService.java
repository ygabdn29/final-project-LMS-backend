package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Progress;
import com.example.demo.service.generic.GenericService;

public interface ProgressService extends GenericService<Progress, Integer>{
  public List<Progress> findProgressByCourseId(Integer courseId);
}
