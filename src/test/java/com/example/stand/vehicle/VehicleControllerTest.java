package com.example.stand.vehicle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = VehicleController.class,
            excludeAutoConfiguration = SecurityAutoConfiguration.class)
@MockBean(VehicleService.class)
class VehicleControllerTest {
    @Autowired
    private MockMvc mvc;

    @ParameterizedTest
    @ValueSource(longs = {5L, 7L, 10L})
    @DisplayName(value = "Should return all the vehicles")
    void shouldReturnAllVehicles(long limit) throws Exception {
        final var request = MockMvcRequestBuilders.get("/list/" + limit); // given
        final var result = mvc.perform(request).andReturn(); // when
        assertThat(result.getResponse().getBufferSize()).isGreaterThan(0); // then
    }

    @Test
    void getVehicleByRegistration() {
    }

    @Test
    void getVehiclesByBrandAndModel() {
    }

    @Test
    void getVehiclesByMotor() {
    }

    @Test
    void getVehiclesByFuel() {
    }

    @Test
    void registerNewVehicle() {
    }

    @Test
    void updateVehicle() {
    }

    @Test
    void removeVehicle() {
    }
}