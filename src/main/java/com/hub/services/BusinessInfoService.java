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

    public Iterable<Department> findAllDepartments(){

        return departmentRepository.findAll();
    }

    public Iterable<Franchise> findAllFranchises(){

        return franchiseRepository.findAll();
    }

    public Optional<Department> findDepartmentById(Integer dptID){

        Optional<Department> department = departmentRepository.findById(dptID);

        if(department.isPresent()){
            return department;
        }
        throw new HubNotFoundException("Could not find department for departmentID: " + dptID);
    }

    public Optional<Franchise> findFranchiseById(Integer frID){

        Optional<Franchise> franchise = franchiseRepository.findById(frID);

        if(franchise.isPresent()){
            return franchise;
        }
        throw new HubNotFoundException("Could not find franchise for franchiseID: " + frID);
    }

    public Department addDepartment(Department department){

        departmentRepository.save(department);

        return department;
    }

    public Franchise addFranchise(Franchise franchise){

        franchiseRepository.save(franchise);

        return franchise;
    }

    public Department findDepartmentByDptName(String dptName){

        return departmentRepository.findByDptName(dptName);
    }

    public Franchise findFranchiseByFrName(String frName){

        return franchiseRepository.findByFrName(frName);
    }
}
