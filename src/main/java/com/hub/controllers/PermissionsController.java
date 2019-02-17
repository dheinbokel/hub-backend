package com.hub.controllers;

import com.hub.daos.PermissionsRepository;
import com.hub.models.HubPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class PermissionsController {

    @Autowired
    private PermissionsRepository permissionsRepository;

    @RequestMapping(value = "/permissions", method = RequestMethod.GET)
    public @ResponseBody Iterable<HubPermissions> getAllPermissions(){
        return permissionsRepository.findAll();
    }

    @RequestMapping(value = "/permissions/{id}", method = RequestMethod.GET)
    public HubPermissions getPermissionById(@PathVariable(value = "id") Integer prmID){
        return permissionsRepository.getOne(prmID);
    }
}
