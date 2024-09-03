package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CourseTransaction;

@Repository
public interface CourseTransactionRepository extends JpaRepository<CourseTransaction, Integer>{
}
