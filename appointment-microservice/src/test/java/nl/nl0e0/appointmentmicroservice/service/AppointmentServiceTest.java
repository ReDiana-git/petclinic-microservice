package nl.nl0e0.appointmentmicroservice.service;

import nl.nl0e0.appointmentmicroservice.entity.CreateAppointmentDTO;
import nl.nl0e0.appointmentmicroservice.entity.SetStateDTO;
import nl.nl0e0.appointmentmicroservice.entity.model.AppointmentState;
import nl.nl0e0.appointmentmicroservice.repository.AppointmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@RestClientTest(AppointmentService.class)
public class AppointmentServiceTest {

    @Autowired
    private AppointmentService appointmentService;

    private MockRestServiceServer server;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void createAppointmentTest(){
        this.server = MockRestServiceServer.createServer(appointmentService.getRestTemplate());
        server.expect(requestTo("http://localhost:8081/appointment/createConsultation"))
                .andRespond(withSuccess("{\"status\":\"success\"}", MediaType.APPLICATION_JSON));
        server.expect(requestTo("http://localhost:8082/appointment/createPayment"))
                .andRespond(withSuccess("{\"status\":\"success\"}", MediaType.APPLICATION_JSON));
        server.expect(requestTo("http://localhost:8083/appointment/createMedicine"))
                .andRespond(withSuccess("{\"status\":\"success\"}", MediaType.APPLICATION_JSON));

        CreateAppointmentDTO createAppointmentDTO = new CreateAppointmentDTO();
        createAppointmentDTO.setAppointmentDate(LocalDateTime.now());
        createAppointmentDTO.setOwnerId(1);
        createAppointmentDTO.setPetId(1);
        createAppointmentDTO.setVetId(1);
        appointmentService.createAppointment(createAppointmentDTO);

        server.verify();
        server = null;
    }

    @Test
    public void checkCreateAppointmentDTOValidationTest(){
        CreateAppointmentDTO createAppointmentDTO = new CreateAppointmentDTO();
        String exceptionString = "";
        try{
            appointmentService.checkCreateAppointmentDTOValidation(createAppointmentDTO);
        }catch (Exception e){
            exceptionString = e.getMessage();
        }
        assertThat(exceptionString).isEqualTo("Appointment Date should not be null.");
    }

    @Test
    public void deleteAllTest(){
        this.server = MockRestServiceServer.createServer(appointmentService.getRestTemplate());
        server.expect(requestTo("http://localhost:8081/appointment/deleteConsultation"))
                .andRespond(withSuccess("{\"status\":\"success\"}", MediaType.APPLICATION_JSON));
        server.expect(requestTo("http://localhost:8082/appointment/deletePayment"))
                .andRespond(withSuccess("{\"status\":\"success\"}", MediaType.APPLICATION_JSON));
        server.expect(requestTo("http://localhost:8083/appointment/deleteMedicine"))
                .andRespond(withSuccess("{\"status\":\"success\"}", MediaType.APPLICATION_JSON));

        appointmentService.deleteAll();

        assertThat(appointmentRepository.findAll()).isEmpty();
        server = null;
    }
}
