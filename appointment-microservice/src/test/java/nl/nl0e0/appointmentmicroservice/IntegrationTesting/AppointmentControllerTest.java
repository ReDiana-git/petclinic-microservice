package nl.nl0e0.appointmentmicroservice.IntegrationTesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.nl0e0.appointmentmicroservice.entity.CreateAppointmentDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class AppointmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void testCreateAppointment() throws Exception {
        // Create your test data, e.g., a CreateAppointmentDTO
        CreateAppointmentDTO testData = new CreateAppointmentDTO(/* fill in your test data */);
        testData.setPetId(2);
        testData.setOwnerId(1);
        testData.setVetId(1);
        testData.setAppointmentDate(LocalDateTime.now());

        // Convert the test data to JSON
        String jsonTestData = objectMapper.writeValueAsString(testData);

        // Perform the request and assert the response
        ResultActions result = mockMvc.perform(post("/appointment/createAppointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonTestData))
                .andExpect(status().isCreated()); // Assert the HTTP status code

    }

    @Test
    public void testDelete() throws Exception{
        ResultActions result = mockMvc.perform(post("/delete"))
                .andExpect(status().isOk());
    }
}
