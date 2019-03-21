package com.hub.services;

import com.hub.daos.DepartmentRepository;
import com.hub.daos.FranchiseRepository;
import com.hub.exceptions.HubNotFoundException;
import com.hub.models.Department;
import com.hub.models.Franchise;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusinessInfoService {

    private DepartmentRepository departmentRepository;
    private FranchiseRepository franchiseRepository;

    BusinessInfoService(DepartmentRepository departmentRepository, FranchiseRepository franchiseRepository){

        this.departmentRepository = departmentRepository;
        this.franchiseRepository = franchiseRepository;
    }

    /**
     * Finds and returns all departments as an iterable list of departments.
     * @return
     */
    public Iterable<Department> findAllDepartments(){

        return departmentRepository.findAll();
    }

    /**
     * Finds and returns all franchises as an iterable list of franchises.
     * @return
     */
    public Iterable<Franchise> findAllFranchises(){

        return franchiseRepository.findAll();
    }

    /**
     * Finds and returns the department with the associated id sent in the path variable. If null, an exception is thrown
     * in the service.
     * @return
     */
    public Optional<Department> findDepartmentById(Integer dptID){

        Optional<Department> department = departmentRepository.findById(dptID);

        if(department.isPresent()){
            return department;
        }
        throw new HubNotFoundException("Could not find department for departmentID: " + dptID);
    }

    /**
     * Finds and returns a franchise with the id associated that was sent in as a path variable. If null, an exception is
     * thrown in the service.
     * @return
     */
    public Optional<Franchise> findFranchiseById(Integer frID){

        Optional<Franchise> franchise = franchiseRepository.findById(frID);

        if(franchise.isPresent()){
            return franchise;
        }
        throw new HubNotFoundException("Could not find franchise for franchiseID: " + frID);
    }

    /**
     * Adds a new department to the database. Takes in a @RequestBody Department object and sends back the record that
     * was saved in the database as a Department.
     * @return
     */
    public Department addDepartment(Department department){

        departmentRepository.save(department);

        return department;
    }

    /**
     * Adds a new franchise to the database. Takes in a @RequestBody Franchise object and sends back the record that
     * was saved in the database as a Franchise.
     * @return
     */
    public Franchise addFranchise(Franchise franchise){

        franchiseRepository.save(franchise);

        return franchise;
    }

    /**
     * Finds and returns a department that shares a name with the path variable that is sent in.
     * @param dptName
     * @return
     */
    public Department findDepartmentByDptName(String dptName){

        return departmentRepository.findByDptName(dptName);
    }

    /**
     * Finds and returns a franchise that shares a name with the path variable that is sent in.
     * @param frName
     * @return
     */
    public Franchise findFranchiseByFrName(String frName){

        return franchiseRepository.findByFrName(frName);
    }
}
