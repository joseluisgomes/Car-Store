package com.example.stand.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping(path = "/list/{limit}")
    public ResponseEntity<List<Employee>> getAllEmployees(@PathVariable long limit) {
        final var employees = service.getAllEmployees(limit);
        return ResponseEntity.ok(employees);
    }

    @GetMapping(path = "/get/{id}")
 //   @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
        return ResponseEntity.ok(service.getEmployeeById(id));
    }
}
