package nl.nl0e0.consultationmicroservice.service;

import nl.nl0e0.consultationmicroservice.entity.consultation.UpdateConsultationDTO;
import nl.nl0e0.consultationmicroservice.repository.ConsultationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.junit.runner.Request.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest
public class ConsultationServiceTest {
    @Autowired
    private ConsultationService consultationService;
    private MockRestServiceServer server;
    @Autowired
    private ConsultationRepository consultationRepository;
    String recordBody = "{\n" +
            "    \"id\": \"317498db-9841-408a-b01d-6f7d4d8da4fc\",\n" +
            "    \"ownerId\": 1,\n" +
            "    \"vetId\": 1,\n" +
            "    \"state\": \"consultation\",\n" +
            "    \"petId\": 2,\n" +
            "    \"appointmentId\": \"2056e53e-72b7-4110-9afb-b219d2907dec\",\n" +
            "    \"consultationId\": \"70c3ad0c-547e-458f-901b-76c1a9dce9be\",\n" +
            "    \"paymentId\": \"2536026b-4b7b-4d1b-86c3-72214e952a23\",\n" +
            "    \"medicineId\": \"c3f865b1-4b3c-4c0a-9bc2-7c703f2ba99e\",\n" +
            "    \"state2String\": \"consultation\"\n" +
            "}";
    String medicineBody = "{\n" +
            "    \"id\": \"c3f865b1-4b3c-4c0a-9bc2-7c703f2ba99e\",\n" +
            "    \"medicines\": \"\"\n" +
            "}";
    @BeforeEach
    public void setUp() {

    }
    @Test
    public void testCheckConsultation(){
        this.server = MockRestServiceServer.createServer(consultationService.consultationRestTemplate.getRestTemplate());
        server.expect(requestTo("http://localhost:8080/appointment/medicalRecord/317498db-9841-408a-b01d-6f7d4d8da4fc"))
                .andRespond(withSuccess(recordBody, MediaType.APPLICATION_JSON));

        server.expect(requestTo("http://localhost:8083/appointment/medicine/c3f865b1-4b3c-4c0a-9bc2-7c703f2ba99e"))
                .andRespond(withSuccess(medicineBody, MediaType.APPLICATION_JSON));
        server.expect(requestTo("http://localhost:8080/appointment/setState"))
                .andRespond(withSuccess("{\"status\":\"success\"}", MediaType.APPLICATION_JSON));
        String recordId = "317498db-9841-408a-b01d-6f7d4d8da4fc";
        consultationService.checkConsultation(recordId);
        server.verify();
        server = null;
    }
    @Test
    public void testUpdateConsultation(){
        this.server = MockRestServiceServer.createServer(consultationService.consultationRestTemplate.getRestTemplate());
        server.expect(requestTo("http://localhost:8080/appointment/medicalRecord/317498db-9841-408a-b01d-6f7d4d8da4fc"))
                .andRespond(withSuccess(recordBody, MediaType.APPLICATION_JSON));
        server.expect(requestTo("http://localhost:8083/appointment/medicine"))
                .andRespond(withSuccess("{\"status\":\"success\"}", MediaType.APPLICATION_JSON));
        server.expect(requestTo("http://localhost:8080/appointment/setState"))
                .andRespond(withSuccess("{\"status\":\"success\"}", MediaType.APPLICATION_JSON));

        UpdateConsultationDTO updateConsultationDTO = new UpdateConsultationDTO();
        updateConsultationDTO.setSymptom("cough");
        updateConsultationDTO.setMedicines("Aspirin");
        updateConsultationDTO.setRecordId("317498db-9841-408a-b01d-6f7d4d8da4fc");
        consultationService.updateConsultation(updateConsultationDTO);
        server.verify();
        server = null;
    }
}
