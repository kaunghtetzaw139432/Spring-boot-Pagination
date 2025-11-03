package com.pagination.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.pagination.model.Employee;
import com.pagination.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class EmployeeController {
    @Autowired
    private final EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<Employee> add(@RequestBody Employee employee) {
        Employee employee2 = employeeService.addEmployee(employee);
        return ResponseEntity.ok(employee2);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> all(@RequestParam(defaultValue = "1", required = false) int pageNo,
            @RequestParam(defaultValue = "3", required = false) int pageSize,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "ASC") String sortDir,
            @RequestParam(required = false) String search) {
        Sort sort = null;
        if (sortDir.equalsIgnoreCase("ASC")) {
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending(); 
        }
        List<Employee> emploee = employeeService.all(PageRequest.of(pageNo - 1, pageSize, sort), search);
        return ResponseEntity.ok(emploee);
    }

}
