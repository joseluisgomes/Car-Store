package com.example.stand.vehicle;

import com.example.stand.Fuel;
import com.example.stand.Motor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class VehicleService {
    private static final String NOT_REGISTERED = "Registration not registered";
    private final VehicleRepository repository;

    @Autowired
    public VehicleService(VehicleRepository repository) {
        this.repository = repository;
    }

    public Vehicle findVehicleByRegistration(String registration) {
        log.info("Fetching vehicle with registration {}", registration);
        return repository.findByRegistration(registration)
                .orElseThrow(() -> new IllegalStateException(NOT_REGISTERED));
    }

    public List<Vehicle> findVehiclesByBrandAndModel(String brand, String model) {
        log.info("Fetching vehicles of {}, whose model is {}", brand, model);
        return repository.findByBrandAndModel(brand, model);
    }

    public List<Vehicle> findVehiclesByMotor(Motor motor) {
        log.info("Fetching vehicles which motor type is: {}", motor.getMotor());
        return repository.findByMotor(motor);
    }

    public List<Vehicle> findVehiclesByFuel(Fuel fuel) {
        log.info("Fetching vehicles which fuel type is: {}", fuel.getFuel());
        return repository.findByFuel(fuel);
    }

    @Transactional
    public Vehicle registerNewVehicle(@NonNull Vehicle vehicle) {
        log.info("Saving {} to the database", vehicle);
        return repository.save(vehicle.clone());
    }

    @Transactional
    public Vehicle updateVehicle(@NonNull String registration,
                                 @NonNull String color) {
        log.info("Updating vehicle with registration {}", registration);
        final var vehicle = repository.findByRegistration(registration)
                .orElseThrow(() -> new IllegalStateException(NOT_REGISTERED));
        vehicle.setColor(color);
        return repository.save(vehicle.clone());
    }

    @Transactional
    public Vehicle removeVehicle(@NonNull String registration) {
        log.info("Removing vehicle with registration {} from the database", registration);
        final var vehicle = findVehicleByRegistration(registration);
        repository.delete(vehicle);
        return vehicle;
    }
}
