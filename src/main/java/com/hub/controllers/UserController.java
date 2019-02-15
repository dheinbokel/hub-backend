package com.hub.controllers;

import com.hub.daos.DepartmentRepository;
import com.hub.daos.UsersRepository;
import com.hub.models.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public @ResponseBody Iterable<Department> getAllContent(){
        return departmentRepository.findAll();
    }
}
