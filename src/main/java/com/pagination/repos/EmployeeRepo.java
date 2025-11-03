package com.pagination.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pagination.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
         Page<Employee> findByname(String name,Pageable pageable);
}
