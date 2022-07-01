package com.example.stand.employee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EmployeeService {
    private final EmployeeRepository repository;

    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAllEmployees(long limit) {
        log.info("Fetching all employees");
        final List<Employee> employees = new ArrayList<>();
        repository.findAll().forEach(employee -> employees.add(employee.clone()));
        return employees.stream()
                .limit(limit)
                .toList();
    }

    public Employee getEmployeeById(long id) {
        log.info("Fetching employee whose id is {}", id);
        return repository.findById(id)
                .orElseThrow()
                .clone();
    }
}
