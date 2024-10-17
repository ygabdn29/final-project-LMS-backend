package com.example.demo.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Parameter;
import com.example.demo.repository.ParameterRepository;
import com.example.demo.service.ParameterService;

@Service
public class ParameterServiceImpl implements ParameterService{
    @Autowired
    private ParameterRepository parameterRepository;

    @Override
    public List<Parameter> get() {
        return parameterRepository.findAll();
    }

    @Override
    public Parameter get(Integer id) {
        return parameterRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean save(Parameter entity) {
        parameterRepository.save(entity);
        return parameterRepository.findById(entity.getId()).isPresent();
    }

    @Override
    public Boolean delete(Integer id) {
        parameterRepository.deleteById(id);
        return parameterRepository.findById(id).isEmpty();
    }

    @Override
    public List<Parameter> getMetadata() {
        return parameterRepository.findByCategory("Metadata");
    }
}
