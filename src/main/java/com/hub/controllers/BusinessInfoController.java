package com.hub.controllers;

import com.hub.models.Department;
import com.hub.models.Franchise;
import com.hub.services.BusinessInfoService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * This class listens and responds to calls from the front end that have to do with departments and franchises, whether
 * adding or getting or any other CRUD activity.
 * Created by Doug Heinbokel on 3/1/19
 */
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class BusinessInfoController {

    private BusinessInfoService businessInfoService;

    BusinessInfoController(BusinessInfoService businessInfoService){

        this.businessInfoService = businessInfoService;
    }

    /**
     * Finds and returns all departments as an iterable list of departments.
     * @return
     */
    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public @ResponseBody Iterable<Department> getAllDepartments(){
        return businessInfoService.findAllDepartments();
    }

    /**
     * Finds and returns the department with the associated id sent in the path variable. If null, an exception is thrown
     * in the service.
     * @return
     */
    @RequestMapping(value = "/departments/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Department> getDepartmentById(@PathVariable(value = "id") Integer dptID){
        return businessInfoService.findDepartmentById(dptID);
    }

    /**
     * Finds and returns all franchises as an iterable list of franchises.
     * @return
     */
    @RequestMapping(value = "/franchises", method = RequestMethod.GET)
    public @ResponseBody Iterable<Franchise> getAllFranchises(){
        return businessInfoService.findAllFranchises();
    }

    /**
     * Finds and returns a franchise with the id associated that was sent in as a path variable. If null, an exception is
     * thrown in the service.
     * @return
     */
    @RequestMapping(value = "/franchises/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Franchise> getFranchiseById(@PathVariable(value = "id") Integer frID){
        return businessInfoService.findFranchiseById(frID);
    }

    /**
     * Adds a new department to the database. Takes in a @RequestBody Department object and sends back the record that
     * was saved in the database as a Department.
     * @return
     */
    @RequestMapping(value = "/departments/add", method = RequestMethod.POST)
    public @ResponseBody Department addDepartment(@RequestBody Department department){
        businessInfoService.addDepartment(department);
        return department;
    }

    /**
     * Adds a new franchise to the database. Takes in a @RequestBody Franchise object and sends back the record that
     * was saved in the database as a Franchise.
     * @return
     */
    @RequestMapping(value = "/franchises/add", method = RequestMethod.POST)
    public @ResponseBody Franchise addFranchise(@RequestBody Franchise franchise){
        businessInfoService.addFranchise(franchise);
        return franchise;
    }

    /**
     * Finds and returns a franchise that shares a name with the path variable that is sent in.
     * @param frName
     * @return
     */
    @RequestMapping(value = "/franchises/byname/{frName}", method = RequestMethod.GET)
    public @ResponseBody Franchise getFranchiseByName(@PathVariable(value = "frName") String frName){
        return businessInfoService.findFranchiseByFrName(frName);
    }

    /**
     * Finds and returns a department that shares a name with the path variable that is sent in.
     * @param dptName
     * @return
     */
    @RequestMapping(value = "/departments/byname/{dptName}", method = RequestMethod.GET)
    public @ResponseBody Department getDepartmentByName(@PathVariable(value = "dptName") String dptName){
        return businessInfoService.findDepartmentByDptName(dptName);
    }
}
