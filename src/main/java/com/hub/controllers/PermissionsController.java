package com.hub.controllers;

import com.hub.models.HubPermissions;
import com.hub.services.PermissionService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * This class was created By Doug Heinbokel on 3/1/19. It handles calls that pertain to returning the 2 kinds of permissions
 * for the Hub.
 */
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class PermissionsController {

    private PermissionService permissionService;

    PermissionsController(PermissionService permissionService){
        this.permissionService = permissionService;
    }

    /**
     * Returns an iterable of HubPermissions from the database.
     * @return Iterable list of HubPermission objects
     */
    @RequestMapping(value = "/permissions", method = RequestMethod.GET)
    public @ResponseBody Iterable<HubPermissions> getAllPermissions(){
        return permissionService.getAllPermissions();
    }

    /**
     * Returns a permission from the database associated with the id in the path variable.
     * @param prmID Integer ID of the permission
     * @return Optional HubPermission
     */
    @RequestMapping(value = "/permissions/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<HubPermissions> getPermissionById(@PathVariable(value = "id") Integer prmID){
        return permissionService.findPermissionById(prmID);
    }


}
