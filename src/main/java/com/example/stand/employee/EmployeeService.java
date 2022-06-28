package com.example.stand.employee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeService {
    private final EmployeeRepository repository;

    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAllEmployees() {
        log.info("Fetching all employees");
        return repository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        log.info("Fetching employee whose id is {}", id);
        return repository.getReferenceById(id).clone();
    }
}
