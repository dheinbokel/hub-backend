package com.hub.controllers;

import com.hub.daos.DepartmentRepository;
import com.hub.daos.FranchiseRepository;
import com.hub.models.Department;
import com.hub.models.Franchise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Department getDepartmentById(@PathVariable(value = "id") Integer dptID){
        return departmentRepository.getOne(dptID);
    }

    @RequestMapping(value = "/franchises", method = RequestMethod.GET)
    public @ResponseBody Iterable<Franchise> getAllFranchises(){
        return franchiseRepository.findAll();
    }

    @RequestMapping(value = "/franchises/{id}", method = RequestMethod.GET)
    public Franchise getFranchiseById(@PathVariable(value = "id") Integer frID){
        return franchiseRepository.getOne(frID);
    }
}
