package com.hub.daos;

import com.hub.models.HubPermissions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionsRepository extends CrudRepository<HubPermissions, Integer> {
}
