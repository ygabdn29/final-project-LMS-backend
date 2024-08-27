package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CourseUserRole;

@Repository
public interface CourseUserRoleRepository extends JpaRepository<CourseUserRole, Integer>{
  
}
