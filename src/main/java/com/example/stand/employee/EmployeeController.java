package com.example.stand.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
        final var employees = service.getAllEmployees().stream()
                .limit(limit)
                .toList();
        return ResponseEntity.ok(employees);
    }

    @GetMapping(path = "get/{id}")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
        return ResponseEntity.ok(service.getEmployeeById(id));
    }


    @GetMapping(path = "pic/{id}") // Get profile pic
    public ResponseEntity<FileSystemResource> getEmployeeProfilePic(@PathVariable long id) throws IOException {
        final var inputFile = service.getEmployeeById(id).getImageUrl();
        final var path = new File(inputFile).toPath();
        final var resource = new FileSystemResource(path);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(Files.probeContentType(path)))
                .body(resource);
    }
}
