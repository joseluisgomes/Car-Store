package com.example.stand.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employee")
@EnableGlobalMethodSecurity(securedEnabled = true)
public class EmployeeController {
    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping(path = "/list")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam(defaultValue = "10") String limit) {
        final var employees = service.getAllEmployees(limit);
        return ResponseEntity.ok(employees);
    }

    @GetMapping(path = "/get")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Employee> getEmployeeById(@RequestParam(defaultValue = "1") String id) {
        return ResponseEntity.ok(service.getEmployeeById(id));
    }
}
