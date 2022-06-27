package com.example.stand.vehicle;

import com.example.stand.Fuel;
import com.example.stand.Motor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(path = "/vehicle")
public class VehicleController {
    private final VehicleService service;

    @Autowired
    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @GetMapping(path = "/find/{registration}")
    public ResponseEntity<Vehicle> findVehicleByRegistration(@PathVariable String registration) {
        final var vehicle = service.findVehicleByRegistration(registration);
        return ResponseEntity.ok(vehicle);
    }

    @GetMapping(path = "/list/{limit}/{brand}/{model}")
    public ResponseEntity<List<Vehicle>> findVehiclesByBrandAndModel(@PathVariable long limit,
                                                                     @PathVariable String brand,
                                                                     @PathVariable String model) {
        final var vehicles = service
                .findVehiclesByBrandAndModel(brand, model)
                .stream()
                .limit(limit)
                .toList();
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping(path = "/list/{limit}/{motor}")
    public ResponseEntity<List<Vehicle>> findVehiclesByMotor(@PathVariable Motor motor, @PathVariable long limit) {
        final var vehicles = service.findVehiclesByMotor(motor)
                .stream()
                .limit(limit)
                .toList();
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping(path = "/list/{limit}/{fuel}")
    public ResponseEntity<List<Vehicle>> findVehiclesByMotor(@PathVariable Fuel fuel,
                                                             @PathVariable long limit) {
        final var vehicles = service.findVehiclesByFuel(fuel)
                .stream()
                .limit(limit)
                .toList();
        return ResponseEntity.ok(vehicles);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<Vehicle> registerNewVehicle(@RequestBody Vehicle vehicle) {
        return ResponseEntity.status(CREATED)
                .body(service.registerNewVehicle(vehicle));
    }

    @PutMapping(path = "/update/{registration}/{color}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable String registration,
                                                 @PathVariable String color) {
        return ResponseEntity.status(ACCEPTED)
                .body(service.updateVehicle(registration, color));
    }

    @DeleteMapping(path = "/delete/{registration}")
    public ResponseEntity<Vehicle> removeVehicle(@PathVariable String registration) {
        return ResponseEntity.ok(service.removeVehicle(registration));
    }
}
