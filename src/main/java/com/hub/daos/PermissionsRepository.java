package com.hub.daos;

import com.hub.models.HubPermissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionsRepository extends JpaRepository<HubPermissions, Integer> {
}
