package com.invex.employee.infrastructure.persistence.repository;

import com.invex.employee.infrastructure.persistence.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    List<EmployeeEntity> findByFirstNameContainingIgnoreCase(String firstName);
}
