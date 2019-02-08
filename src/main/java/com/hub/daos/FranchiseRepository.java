package com.hub.daos;

import com.hub.models.Franchise;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranchiseRepository extends CrudRepository<Franchise, Integer> {
}