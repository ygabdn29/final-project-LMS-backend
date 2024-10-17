package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.handler.Utils;
import com.example.demo.model.Parameter;
import com.example.demo.service.ParameterService;

@RestController
@RequestMapping("api/metadata")
public class MetadataRestController {
    @Autowired
    private ParameterService parameterService;

    @GetMapping("get")
    public ResponseEntity<Object> getMetadata() {
        List<Parameter> parameters = parameterService.getMetadata();
        if (parameters != null && !parameters.isEmpty()) {
            return Utils.generateResponseEntity(HttpStatus.OK, "Success retrieve data", parameters);
        }
        return Utils.generateResponseEntity(HttpStatus.BAD_REQUEST, "No data yet!");
    }

    @PostMapping("edit/{id}")
    public ResponseEntity<Object> editMetadata(@PathVariable Integer id, @RequestBody Parameter parameter) {
        Parameter editParameter = parameterService.get(id);
        if(editParameter != null){
            editParameter = new Parameter(id, parameter.getName(), parameter.getValue(), parameter.getCategory());
            parameterService.save(editParameter);
            return Utils.generateResponseEntity(HttpStatus.OK, "Metadata Has been Updated", editParameter);
        }
        return Utils.generateResponseEntity(HttpStatus.BAD_REQUEST, "No data");
    }

    @PostMapping("create")
    public ResponseEntity<Object> createMetadata(@RequestBody Parameter parameter) {
            Parameter newParameter = new Parameter(parameter.getId(), parameter.getName(), parameter.getValue(), parameter.getCategory());
            parameterService.save(newParameter);
            return Utils.generateResponseEntity(HttpStatus.OK, "Metadata Has been Updated", newParameter);
    }


}
