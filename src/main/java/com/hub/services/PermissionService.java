package com.hub.services;

import com.hub.daos.PermissionsRepository;
import com.hub.exceptions.HubNotFoundException;
import com.hub.models.HubPermissions;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This class handles the logic and thinking of the data that comes in through the controller and returns permissions.
 * Created by Doug Heinbokel on 3/1/19
 */
@Service
public class PermissionService {

    private PermissionsRepository permissionsRepository;

    PermissionService(PermissionsRepository permissionsRepository){
        this.permissionsRepository = permissionsRepository;
    }

    /**
     * Returns a permission record with the same id as the id passed into the parameters.  If null, it will throw an exception.
     * @param prmID
     * @return
     */
    public Optional<HubPermissions> findPermissionById(Integer prmID){

        Optional<HubPermissions> hubPermissions = permissionsRepository.findById(prmID);

        if(hubPermissions.isPresent()){
            return hubPermissions;
        }
        throw new HubNotFoundException("Could not find permissions for permission ID: " + prmID);
    }

    /**
     * Returns all permissions stored in the database as an iterable of permissions.
     * @return
     */
    public Iterable<HubPermissions> getAllPermissions(){

        return permissionsRepository.findAll();
    }
}
