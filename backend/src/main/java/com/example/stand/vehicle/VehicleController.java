package com.example.stand.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/vehicle")
public class VehicleController {
    private final VehicleService service;

    @Autowired
    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @GetMapping(path = "/find/{registration}")
    public ResponseEntity<Vehicle> findVehicleByRegistration(@PathVariable("registration") String registration) {
        final var vehicle = service.findVehicleByRegistration(registration);
        return new ResponseEntity<>(vehicle, HttpStatus.OK);
    }
}
