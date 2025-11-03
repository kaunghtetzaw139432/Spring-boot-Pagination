package com.pagination.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pagination.model.Employee;
import com.pagination.repos.EmployeeRepo;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    public List<Employee> all(Pageable pageable,String search) {
        if (search == null) {
            return employeeRepo.findAll(pageable).getContent();
        }else{
               return employeeRepo.findByname(search, pageable).getContent();
        }
       
    }

    public Employee get(Long id) {
        return employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("No employee with that id"));
    }

    public Employee addEmployee(Employee employee) {
        Employee employee2 = new Employee();
        employee2.setName(employee.getName());
        employee2.setDept(employee.getDept());
        employee2.setJoiningDate(new Date());
        return employeeRepo.save(employee2);
    }

}
