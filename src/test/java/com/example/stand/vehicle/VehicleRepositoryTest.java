package com.example.stand.vehicle;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static com.example.stand.vehicle.engine.Fuel.DIESEL;
import static com.example.stand.vehicle.engine.Fuel.GASOLINE;
import static com.example.stand.vehicle.engine.Motor.*;

@DataJpaTest
class VehicleRepositoryTest {
    @Autowired
    private VehicleRepository underTest;

    @AfterEach
    void tearDown() { underTest.deleteAll(); }

    @Test
    @DisplayName(value = "Should return the vehicle associated with the given registration")
    void shouldGetTheVehicleByTheGivenRegistration() {
        // given
        final var vehicle = new Vehicle
                .Builder(10L, "Nissan", "0123456", 5, CAR, DIESEL)
                .build();
        underTest.save(vehicle);

        // when
        final var vehiclePretended = underTest.getByRegistration(vehicle.getRegistration())
                .orElseThrow();
        // then
        AssertionsForClassTypes.assertThat(vehicle).isEqualTo(vehiclePretended);
    }

    @Test
    @DisplayName(value = "Should return a list with the vehicles from the given brand and model")
    void shouldGetTheVehiclesOfTheBrandAndModel() {
        // given
        final var vehicle1 = new Vehicle
                .Builder(1L, "BMW", "0123456789", 5, JEEP, GASOLINE)
                .model("X3")
                .build();
        final var vehicle2 = new Vehicle
                .Builder(2L, "BMW", "0125456785", 5, CAR, DIESEL)
                .model("X3")
                .build();
        underTest.saveAll(List.of(vehicle1, vehicle2));

        // when
        final var vehiclesPretended = underTest
                .getByBrandAndModel("BMW", "X3")
                .toArray();

        // then
        AssertionsForClassTypes.assertThat(vehiclesPretended)
                .containsAll(List.of(vehicle1, vehicle2));
    }

    @Test
    @DisplayName(value = "Should return a list with all the vehicles which contain the given motor")
    void getVehiclesByMotor() {
        // given
        final var vehicle1 = new Vehicle
                .Builder(1L, "BMW", "0123456789", 5, VAN, GASOLINE)
                .model("X3")
                .build();
        final var vehicle2 = new Vehicle
                .Builder(2L, "BMW", "0125456785", 5, VAN, DIESEL)
                .model("X3")
                .build();
        underTest.saveAll(List.of(vehicle1, vehicle2));

        // when
        final var vehiclesPretended = underTest.getByMotor(VAN).toArray();

        // then
        AssertionsForClassTypes.assertThat(vehiclesPretended)
                .containsAll(List.of(vehicle1, vehicle2));
    }

    @Test
    @DisplayName(value = "Should return a list with all the vehicles which contain the given fuel")
    void getVehiclesByFuel() {
        // given
        final var vehicle1 = new Vehicle
                .Builder(1L, "BMW", "0123456789", 5, VAN, GASOLINE)
                .model("X3")
                .build();
        final var vehicle2 = new Vehicle
                .Builder(2L, "BMW", "0125456785", 5, VAN, GASOLINE)
                .model("X3")
                .build();
        underTest.saveAll(List.of(vehicle1, vehicle2));

        // when
        final var vehiclesPretended = underTest.getByFuel(GASOLINE).toArray();

        // then
        AssertionsForClassTypes.assertThat(vehiclesPretended)
                .containsAll(List.of(vehicle1, vehicle2));
    }
}