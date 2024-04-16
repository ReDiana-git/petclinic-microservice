package nl.nl0e0.appointmentmicroservice.contract;

import nl.nl0e0.appointmentmicroservice.entity.MedicalRecord;
import nl.nl0e0.appointmentmicroservice.service.AppointmentRestTemplate;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureStubRunner(
        ids = {
                "nl.nl0e0:consultation-microservice:+:stubs:8081",
                "nl.nl0e0:payment-microservice:+:stubs:8082",
                "nl.nl0e0:medicine-microservice:+:stubs:8083"
        },
        stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ConsumerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AppointmentRestTemplate appointmentRestTemplate;

    @Test
    public void testConsumerService() throws Exception {
        // 使用 MockMvc 调用消费者端的端点
        // 消費者端的服務將使用stub來模擬provider
        appointmentRestTemplate.createConsultation("1234");

    }

    @Test
    public void testCreateAppointment() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord();
        appointmentRestTemplate.createNewRecord(medicalRecord);
    }
}
