package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Parameter;
import com.example.demo.service.generic.GenericService;

public interface ParameterService extends GenericService<Parameter, Integer> {
    public List<Parameter> getMetadata();
}
