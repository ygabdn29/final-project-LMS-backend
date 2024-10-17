package com.example.demo.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserRole;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService{
    @Autowired
    private UserRoleRepository userRoleRepository;
  
    @Override
    public List<UserRole> get() {
      return userRoleRepository.findAll();
    }
  
    @Override
    public UserRole get(Integer id) {
      return userRoleRepository.findById(id).orElse(null);
    }
  
    @Override
    public Boolean save(UserRole entity) {
      userRoleRepository.save(entity);
      return userRoleRepository.findById(entity.getId()).isPresent();
    }
  
    @Override
    public Boolean delete(Integer id) {
      userRoleRepository.deleteById(id);
      return userRoleRepository.findById(id).isEmpty();
    }
}
