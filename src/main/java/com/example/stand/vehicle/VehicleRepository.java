package com.example.stand.vehicle;

import com.example.stand.vehicle.engine.Fuel;
import com.example.stand.vehicle.engine.Motor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("select v from Vehicle v where v.registration = ?1")
    Optional<Vehicle> getByRegistration(@NonNull String registration);

    @Query("select v from Vehicle v where v.brand = ?1 and v.model = ?2")
    List<Vehicle> getByBrandAndModel(@NonNull String brand, @NonNull String model);

    @Query("select v from Vehicle v where v.motor = ?1")
    List<Vehicle> getByMotor(@NonNull Motor motor);

    @Query("select v from Vehicle v where v.fuel = ?1")
    List<Vehicle> getByFuel(@NonNull Fuel fuel);
}