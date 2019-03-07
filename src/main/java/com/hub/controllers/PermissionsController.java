package com.hub.controllers;

import com.hub.models.HubPermissions;
import com.hub.services.PermissionService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class PermissionsController {

    private PermissionService permissionService;

    PermissionsController(PermissionService permissionService){
        this.permissionService = permissionService;
    }

    @RequestMapping(value = "/permissions", method = RequestMethod.GET)
    public @ResponseBody Iterable<HubPermissions> getAllPermissions(){
        return permissionService.getAllPermissions();
    }

    @RequestMapping(value = "/permissions/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<HubPermissions> getPermissionById(@PathVariable(value = "id") Integer prmID){
        return permissionService.findPermissionById(prmID);
    }


}
