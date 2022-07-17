package com.example.stand.vehicle;

import com.example.stand.employee.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.stand.vehicle.VehicleController.VEHICLE_NUM;
import static com.example.stand.vehicle.VehicleController.VEHICLE_REGISTRATION;
import static com.example.stand.vehicle.engine.Fuel.GASOLINE;
import static com.example.stand.vehicle.engine.Motor.CAR;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@WithMockUser
@MockBean(EmployeeService.class)
class VehicleControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private VehicleService vehicleService;
    private static Vehicle vehicle;

    @BeforeEach
    void setUp() {
        vehicle = new Vehicle
                .Builder(1L, "Nissan", "01234567", 5, CAR, GASOLINE)
                .model("JUKE")
                .build();
    }

    @Test
    @DisplayName("Should get all vehicles")
    void shouldGetAllVehicles() throws Exception {
        mockMvc.perform(get("/vehicle/list"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(vehicleService).getAllVehicles(VEHICLE_NUM);
    }

    @Test
    @DisplayName("Should get the vehicle with the given registration")
    void shouldGetVehicleByRegistration() throws Exception {
        mockMvc.perform(get("/vehicle/get"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(vehicleService).getVehicleByRegistration(VEHICLE_REGISTRATION);
    }

    @Test
    @DisplayName("Should get all vehicles of the given brand and model")
    void shouldGetVehiclesByBrandAndModel() throws Exception {
        mockMvc.perform(get("/vehicle/brand/list"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(vehicleService)
                .getVehiclesByBrandAndModel("Nissan", "Juke", VEHICLE_NUM);
    }

    @Test
    @DisplayName("Should get all vehicles which have the given motor")
    void shouldGetVehiclesByMotor() throws Exception {
        mockMvc.perform(get("/vehicle/motor/list"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(vehicleService).getVehiclesByMotor("CAR", VEHICLE_NUM);
    }

    @Test
    @DisplayName("Should get all vehicles which have the given fuel")
    void shouldGetVehiclesByFuel() throws Exception {
        mockMvc.perform(get("/vehicle/fuel/list"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(vehicleService).getVehiclesByFuel("Gasoline", VEHICLE_NUM);
    }

    @Test
    @DisplayName("Should register the given vehicle")
    void shouldRegisterNewVehicle() throws Exception {
        mockMvc.perform(post("/vehicle/registration", vehicle).with(csrf()))
                .andDo(print())
                .andExpect(status().isCreated());
        verify(vehicleService).registerNewVehicle(vehicle);
    }

    @Test
    void shouldUpdateVehicle() throws Exception {
        mockMvc.perform(put("/vehicle/update", VEHICLE_REGISTRATION, "Pink").with(csrf()))
                .andDo(print())
                .andExpect(status().isAccepted());
        verify(vehicleService).updateVehicle(VEHICLE_REGISTRATION, "Black");
    }

    @Test
    void shouldRemoveVehicle() throws Exception {
        mockMvc.perform(delete("/vehicle/delete", VEHICLE_REGISTRATION).with(csrf()))
                .andDo(print())
                .andExpect(status().isOk());
        verify(vehicleService).removeVehicle(VEHICLE_REGISTRATION);
    }
}