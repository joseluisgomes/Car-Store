package com.example.stand.vehicle;

import com.example.stand.employee.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest
@MockBean({VehicleService.class, EmployeeService.class})
class VehicleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName(value = "Should get all vehicles")
    @WithMockUser
    void shouldGetAllVehicles() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicle/list"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
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