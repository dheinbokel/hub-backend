package com.hub.services;

import com.hub.daos.PermissionsRepository;
import com.hub.exceptions.HubNotFoundException;
import com.hub.models.HubPermissions;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PermissionService {

    private PermissionsRepository permissionsRepository;

    PermissionService(PermissionsRepository permissionsRepository){
        this.permissionsRepository = permissionsRepository;
    }

    public Optional<HubPermissions> findPermissionById(Integer prmID){

        Optional<HubPermissions> hubPermissions = permissionsRepository.findById(prmID);

        if(hubPermissions.isPresent()){
            return hubPermissions;
        }
        throw new HubNotFoundException("Could not find permissions for permission ID: " + prmID);
    }

    public Iterable<HubPermissions> getAllPermissions(){

        return permissionsRepository.findAll();
    }
}
