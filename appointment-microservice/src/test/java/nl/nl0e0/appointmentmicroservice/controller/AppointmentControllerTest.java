package nl.nl0e0.appointmentmicroservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import nl.nl0e0.appointmentmicroservice.entity.CreateAppointmentDTO;
import nl.nl0e0.appointmentmicroservice.service.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class AppointmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    private MockRestServiceServer server;

    @Autowired
    private AppointmentService appointmentService;

    @BeforeEach
    public void setUp(){
        this.server = MockRestServiceServer.createServer(appointmentService.getRestTemplate());
    }

    @Test
    public void testCreateAppointment() throws Exception {
        server.expect(requestTo("http://localhost:8081/appointment/createConsultation"))
                .andRespond(withSuccess("{\"status\":\"success\"}", MediaType.APPLICATION_JSON));
        server.expect(requestTo("http://localhost:8082/appointment/createPayment"))
                .andRespond(withSuccess("{\"status\":\"success\"}", MediaType.APPLICATION_JSON));
        server.expect(requestTo("http://localhost:8083/appointment/createMedicine"))
                .andRespond(withSuccess("{\"status\":\"success\"}", MediaType.APPLICATION_JSON));
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
//                .andExpect(jsonPath("$.yourJsonField").value("expectedValue")); // Assert the response content

        // You can add more assertions as needed based on your application logic
        server = null;
    }

    @Test
    public void testDelete() throws Exception{
        server.expect(requestTo("http://localhost:8081/appointment/deleteConsultation"))
                .andRespond(withSuccess("{\"status\":\"success\"}", MediaType.APPLICATION_JSON));
        server.expect(requestTo("http://localhost:8082/appointment/deletePayment"))
                .andRespond(withSuccess("{\"status\":\"success\"}", MediaType.APPLICATION_JSON));
        server.expect(requestTo("http://localhost:8083/appointment/deleteMedicine"))
                .andRespond(withSuccess("{\"status\":\"success\"}", MediaType.APPLICATION_JSON));
        ResultActions result = mockMvc.perform(post("/delete"))
                .andExpect(status().isOk());
    }
}
