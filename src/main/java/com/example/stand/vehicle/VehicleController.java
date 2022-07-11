package com.example.stand.vehicle;

import com.example.stand.vehicle.engine.Fuel;
import com.example.stand.vehicle.engine.Motor;
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

    // Default vehicle's settings
    private static final String VEHICLE_REGISTRATION = "JN1AY1AP5BM181741";
    private static final String VEHICLE_NUM = "10"; // Number of vehicles to be listed

    @Autowired
    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<Vehicle>> getAllVehicles(@RequestParam(defaultValue = VEHICLE_NUM) String limit) {
        return ResponseEntity.ok(service.getAllVehicles(limit));
    }

    @GetMapping(path = "/get")
    public ResponseEntity<Vehicle> getVehicleByRegistration(@RequestParam(defaultValue = VEHICLE_REGISTRATION) String registration) {
        final var vehicle = service.getVehicleByRegistration(registration);
        return ResponseEntity.ok(vehicle);
    }

    @GetMapping(path = "/brand/list")
    public ResponseEntity<List<Vehicle>> getVehiclesByBrandAndModel(@RequestParam(defaultValue = "Nissan") String brand,
                                                                    @RequestParam(defaultValue = "Juke") String model,
                                                                    @RequestParam(defaultValue = VEHICLE_NUM) String limit) {
        final var vehicles = service.getVehiclesByBrandAndModel(brand, model, limit);
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping(path = "/motor/list")
    public ResponseEntity<List<Vehicle>> getVehiclesByMotor(@RequestParam(defaultValue = "CAR") String motor,
                                                            @RequestParam(defaultValue = VEHICLE_NUM) String limit) {
        final var vehicles = service.getVehiclesByMotor(Motor.valueOf(motor), limit);
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping(path = "/fuel/list")
    public ResponseEntity<List<Vehicle>> getVehiclesByFuel(@RequestParam(defaultValue = "Gasoline") String fuel,
                                                           @RequestParam(defaultValue = VEHICLE_NUM) String limit) {
        final var vehicles = service.getVehiclesByFuel(Fuel.valueOf(fuel), limit);
        return ResponseEntity.ok(vehicles);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<Vehicle> registerNewVehicle(@RequestBody Vehicle vehicle) {
        return ResponseEntity.status(CREATED).body(service.registerNewVehicle(vehicle));
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Vehicle> updateVehicle(@RequestParam(defaultValue = VEHICLE_REGISTRATION) String registration,
                                                 @RequestParam(defaultValue = "Black") String color) {
        return ResponseEntity.status(ACCEPTED).body(service.updateVehicle(registration, color));
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<Vehicle> removeVehicle(@RequestParam(defaultValue = VEHICLE_REGISTRATION) String registration) {
        return ResponseEntity.ok(service.removeVehicle(registration));
    }
}
