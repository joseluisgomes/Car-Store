package com.example.stand.employee;

import com.example.stand.vehicle.VehicleController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@MockBean(VehicleController.class)
class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService employeeService;

    @Test
    @DisplayName("Should get all employees")
    @WithMockUser(roles = {"USER", "ADMIN"})
    void shouldGetAllEmployees() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/list"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(employeeService).getAllEmployees("10");
    }

    @Test
    @DisplayName("Should get the employee with the associated id")
    @WithMockUser(roles = "ADMIN")
    void shouldGetEmployeeById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/get"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(employeeService).getEmployeeById("1");
    }
}