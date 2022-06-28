package com.example.stand.vehicle;

import com.example.stand.vehicle.engine.Fuel;
import com.example.stand.vehicle.engine.Motor;
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

    public Vehicle getVehicleByRegistration(String registration) {
        log.info("Fetching vehicle with registration {}", registration);
        return repository.getByRegistration(registration)
                .orElseThrow(() -> new IllegalStateException(NOT_REGISTERED));
    }

    public List<Vehicle> getVehiclesByBrandAndModel(String brand, String model) {
        log.info("Fetching vehicles of {}, whose model are {}", brand, model);
        return repository.getByBrandAndModel(brand, model);
    }

    public List<Vehicle> getVehiclesByMotor(Motor motor) {
        log.info("Fetching vehicles whose motor type are: {}", motor.getMotor());
        return repository.getByMotor(motor);
    }

    public List<Vehicle> getVehiclesByFuel(Fuel fuel) {
        log.info("Fetching vehicles whose fuel type are: {}", fuel.getFuel());
        return repository.getByFuel(fuel);
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
        final var vehicle = repository.getByRegistration(registration)
                .orElseThrow(() -> new IllegalStateException(NOT_REGISTERED));
        vehicle.setColor(color);
        return repository.save(vehicle.clone());
    }

    @Transactional
    public Vehicle removeVehicle(@NonNull String registration) {
        log.info("Removing vehicle with registration {} from the database", registration);
        final var vehicle = getVehicleByRegistration(registration);
        repository.delete(vehicle);
        return vehicle.clone();
    }
}
