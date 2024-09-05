package com.example.demo.service;

import com.example.demo.model.CourseTransaction;
import com.example.demo.service.generic.GenericService;

public interface CourseTransactionService extends GenericService<CourseTransaction, Integer>{
  CourseTransaction findByUserId(Integer userId);
}
