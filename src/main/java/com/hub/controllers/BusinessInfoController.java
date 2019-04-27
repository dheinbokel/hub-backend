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
     * @param active boolean status of active field
     * @return Iterable list of Department objects
     */
    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public @ResponseBody Iterable<Department> getAllDepartments(@RequestParam(defaultValue = "true", required = false)
                                                                boolean active){

        return businessInfoService.findAllDepartmentsByActiveStatus(active);
    }

    /**
     * Finds and returns the department with the associated id sent in the path variable. If null, an exception is thrown
     * in the service.
     * @param dptID Integer Id of the department in question
     * @return
     */
    @RequestMapping(value = "/departments/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Department> getDepartmentById(@PathVariable(value = "id") Integer dptID){
        return businessInfoService.findDepartmentById(dptID);
    }

    /**
     * Finds and returns all franchises as an iterable list of franchises.
     * @param active boolean status of active field
     * @return Iterable list of franchises
     */
    @RequestMapping(value = "/franchises", method = RequestMethod.GET)
    public @ResponseBody Iterable<Franchise> getAllFranchises(@RequestParam(defaultValue = "true", required = false)
                                                                          boolean active){

        return businessInfoService.findAllFranchisesByActiveStatus(active);
    }

    /**
     * Finds and returns a franchise with the id associated that was sent in as a path variable. If null, an exception is
     * thrown in the service.
     * @param frID Integer ID of franchise
     * @return Optional Franchise
     */
    @RequestMapping(value = "/franchises/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Franchise> getFranchiseById(@PathVariable(value = "id") Integer frID){
        return businessInfoService.findFranchiseById(frID);
    }

    /**
     * Adds a new department to the database. Takes in a @RequestBody Department object and sends back the record that
     * was saved in the database as a Department.
     * @param department Department object
     * @return Department that was just added
     */
    @RequestMapping(value = "/departments/add", method = RequestMethod.POST)
    public @ResponseBody Department addDepartment(@RequestBody Department department){
        businessInfoService.addDepartment(department);
        return department;
    }

    /**
     * Adds a new franchise to the database. Takes in a @RequestBody Franchise object and sends back the record that
     * was saved in the database as a Franchise.
     * @param franchise Franchise object
     * @return Franchise that was just added
     */
    @RequestMapping(value = "/franchises/add", method = RequestMethod.POST)
    public @ResponseBody Franchise addFranchise(@RequestBody Franchise franchise){
        businessInfoService.addFranchise(franchise);
        return franchise;
    }

    /**
     * Finds and returns a franchise that shares a name with the path variable that is sent in.
     * @param frName String name of franchise
     * @return Franchise object
     */
    @RequestMapping(value = "/franchises/byname/{frName}", method = RequestMethod.GET)
    public @ResponseBody Franchise getFranchiseByName(@PathVariable(value = "frName") String frName){
        return businessInfoService.findFranchiseByFrName(frName);
    }

    /**
     * Finds and returns a department that shares a name with the path variable that is sent in.
     * @param dptName String name of department
     * @return Department ojbect
     */
    @RequestMapping(value = "/departments/byname/{dptName}", method = RequestMethod.GET)
    public @ResponseBody Department getDepartmentByName(@PathVariable(value = "dptName") String dptName){
        return businessInfoService.findDepartmentByDptName(dptName);
    }

    /**
     * This endpoint is used to edit a department with the same dptID as what is sent in the path variable.
     * @param department Department object to replace the department in question
     * @param dptID Integer of department being edited
     * @return Department object
     */
    @RequestMapping(value = "/departments/edit/{dptID}", method = RequestMethod.PUT)
    public @ResponseBody Department editDepartment(@RequestBody Department department, @PathVariable(value = "dptID") Integer dptID){

        return businessInfoService.editDepartment(department, dptID);
    }

    /**
     * This endpoint is used to edit a franchise with the same frID as what is sent in the path variable.
     * @param franchise object to replace the franchise in question
     * @param frID Integer of franchise being edited
     * @return Franchise object
     */
    @RequestMapping(value = "/franchises/edit/{frID}", method = RequestMethod.PUT)
    public @ResponseBody Franchise editFranchise(@RequestBody Franchise franchise, @PathVariable(value = "frID") Integer frID){

        return businessInfoService.editFranchise(franchise, frID);
    }

    /**
     * This endpoint toggles tha active status of a franchise back and forth(true and false)
     * @param frID Integer ID of franchise being toggled
     * @return Integer ID of the toggled franchise
     */
    @RequestMapping(value = "/franchises/toggle/{frID}", method = RequestMethod.PUT)
    public @ResponseBody Integer toggleFranchise(@PathVariable(value = "frID") Integer frID){

        return businessInfoService.toggleFranchise(frID);
    }

    /**
     * This endpoint toggles the active status of a department back and forth(true and false)
     * @param dptID Integer of department being toggled
     * @return Integer ID of the toggled department
     */
    @RequestMapping(value = "/departments/toggle/{dptID}", method = RequestMethod.PUT)
    public @ResponseBody Integer toggleDepartments(@PathVariable(value = "dptID") Integer dptID){

        return businessInfoService.toggleDepartment(dptID);
    }
}
