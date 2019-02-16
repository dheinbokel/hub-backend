package com.hub.controllers;

import com.hub.daos.DepartmentRepository;
import com.hub.daos.FranchiseRepository;
import com.hub.models.Department;
import com.hub.models.Franchise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class BusinessInfoController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private FranchiseRepository franchiseRepository;

    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public @ResponseBody Iterable<Department> getAllDepartments(){
        return departmentRepository.findAll();
    }

    @RequestMapping(value = "/departments/{id}", method = RequestMethod.GET)
    public Optional<Department> getDepartmentById(@PathVariable(value = "id") Integer dptID){
        return departmentRepository.findById(dptID);
    }

    @RequestMapping(value = "/franchises", method = RequestMethod.GET)
    public @ResponseBody Iterable<Franchise> getAllFranchises(){
        return franchiseRepository.findAll();
    }
}
