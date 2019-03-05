package com.hub.controllers;

import com.hub.models.Department;
import com.hub.models.Franchise;
import com.hub.services.BusinessInfoService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class BusinessInfoController {

    private BusinessInfoService businessInfoService;

    BusinessInfoController(BusinessInfoService businessInfoService){

        this.businessInfoService = businessInfoService;
    }

    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public @ResponseBody Iterable<Department> getAllDepartments(){
        return businessInfoService.findAllDepartments();
    }

    @RequestMapping(value = "/departments/{id}", method = RequestMethod.GET)
    public Optional<Department> getDepartmentById(@PathVariable(value = "id") Integer dptID){
        return businessInfoService.findDepartmentById(dptID);
    }

    @RequestMapping(value = "/franchises", method = RequestMethod.GET)
    public @ResponseBody Iterable<Franchise> getAllFranchises(){
        return businessInfoService.findAllFranchises();
    }

    @RequestMapping(value = "/franchises/{id}", method = RequestMethod.GET)
    public Optional<Franchise> getFranchiseById(@PathVariable(value = "id") Integer frID){
        return businessInfoService.findFranchiseById(frID);
    }

    @RequestMapping(value = "/departments/add", method = RequestMethod.POST)
    public @ResponseBody Department addDepartment(@RequestBody Department department){
        businessInfoService.addDepartment(department);
        return department;
    }

    @RequestMapping(value = "/franchises/add", method = RequestMethod.POST)
    public @ResponseBody Franchise addFranchise(@RequestBody Franchise franchise){
        businessInfoService.addFranchise(franchise);
        return franchise;
    }
}
