package com.hub.daos;

import com.hub.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Department findByDptName(String dptName);

    Iterable<Department> findByIsActive(boolean isActive);
}
