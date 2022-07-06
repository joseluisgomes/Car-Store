package com.example.stand.vehicle;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static com.example.stand.vehicle.engine.Fuel.DIESEL;
import static com.example.stand.vehicle.engine.Fuel.GASOLINE;
import static com.example.stand.vehicle.engine.Motor.CAR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@DataJpaTest
class VehicleRepositoryTest {
    @Autowired
    private VehicleRepository underTest;
    private static Vehicle vehicle1, vehicle2;
    private static List<Vehicle> vehicles;

    @BeforeEach
    void setUp() {
        vehicle1 = new Vehicle.Builder(1L, "Nissan", "01234567", 5, CAR, GASOLINE)
                .model("JUKE")
                .build();
        vehicle2 = new Vehicle.Builder(2L, "Nissan", "01234568", 5, CAR, DIESEL)
                .model("JUKE")
                .build();
        vehicles = List.of(vehicle1, vehicle2);
    }

    @AfterEach
    void tearDown() { underTest.deleteAll(); }

    @Test
    @DisplayName(value = "Should return the vehicle associated with the given registration")
    void shouldGetTheVehicleByTheGivenRegistration() {
        // given
        underTest.save(vehicle1);

        // when
        final var vehiclePretended = underTest.getByRegistration(vehicle1.getRegistration())
                .orElseThrow();
        // then
        assertThat(vehicle1).isEqualTo(vehiclePretended);
        assertEquals(vehiclePretended.getRegistration(), vehicle1.getRegistration());
    }

    @Test
    @DisplayName(value = "Should return a list with the vehicles of the given brand and model")
    void shouldGetTheVehiclesOfTheGivenBrandAndModel() {
        // given
        underTest.saveAll(vehicles);

        // when
        final var vehiclesPretended = underTest.getByBrandAndModel("Nissan", "JUKE");

        // then
        assertEquals(2, vehiclesPretended.size());
        assertIterableEquals(vehicles, vehiclesPretended);
    }

    @Test
    @DisplayName(value = "Should return a list with all the vehicles which contain the given motor")
    void shouldGetTheVehiclesByMotor() {
        // given
        underTest.saveAll(vehicles);

        // when
        final var carVehicles = underTest.getByMotor(CAR);

        // then
        assertEquals(2, carVehicles.size());
        assertIterableEquals(vehicles, carVehicles);
    }

    @Test
    @DisplayName(value = "Should return a list with all the vehicles which contain the given fuel")
    void shouldGetTheVehiclesByFuel() {
        // given
        underTest.saveAll(vehicles);

        // when
        final var fuelVehicles = underTest.getByFuel(DIESEL);

        // then
        assertEquals(1, fuelVehicles.size());
        assertThat(vehicle2).isEqualTo(fuelVehicles.get(0));
    }
}