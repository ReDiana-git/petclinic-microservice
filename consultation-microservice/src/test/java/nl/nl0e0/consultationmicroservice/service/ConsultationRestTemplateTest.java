package nl.nl0e0.consultationmicroservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.UUID;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

//@RestClientTest(ConsultationRestTemplate.class)
public class ConsultationRestTemplateTest {

//    @Autowired
    private ConsultationRestTemplate restTemplate;
    private MockRestServiceServer server;

    @BeforeEach
    public void setUp(){
        this.server = MockRestServiceServer.createServer(restTemplate.getRestTemplate());

        // 配置Mock Server響應
        server.expect(requestTo("http://localhost:8080/appointment/medicalRecord/05d1b29c-5594-4f66-b289-ed9a86fb1c28"))
                .andRespond(withSuccess("{\"status\":\"success\"}", MediaType.APPLICATION_JSON));
    }

//    @Test
//    public void testGetRecordById(){
//        String recordId = "05d1b29c-5594-4f66-b289-ed9a86fb1c28";
//        restTemplate.getRecordById(recordId);
//
//        server.verify();
//    }
}
