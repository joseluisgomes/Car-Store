package com.example.stand.vehicle;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static com.example.stand.vehicle.engine.Fuel.DIESEL;
import static com.example.stand.vehicle.engine.Motor.CAR;

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
    void getByBrandAndModel() {
    }

    @Test
    void getByMotor() {
    }

    @Test
    void getByFuel() {
    }
}