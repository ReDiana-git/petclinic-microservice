package nl.nl0e0.appointmentmicroservice.service;

import nl.nl0e0.appointmentmicroservice.entity.MedicalRecord;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

//@SpringBootTest
//@RunWith(SpringRunner.class)
//@ExtendWith(SpringExtension.class)
@RestClientTest(AppointmentRestTemplate.class)
public class AppointmentRestTemplateTest {
    @Autowired
    private AppointmentRestTemplate appointmentRestTemplate;
//    @Autowired
    private MockRestServiceServer server;

    @BeforeEach
    public void setUp(){
        this.server = MockRestServiceServer.createServer(appointmentRestTemplate.getRestTemplate());

        // 配置Mock Server響應
        server.expect(requestTo("http://localhost:8081/appointment/createConsultation"))
                .andRespond(withSuccess("{\"status\":\"success\"}", MediaType.APPLICATION_JSON));
        server.expect(requestTo("http://localhost:8082/appointment/createPayment"))
                .andRespond(withSuccess("{\"status\":\"success\"}", MediaType.APPLICATION_JSON));
        server.expect(requestTo("http://localhost:8083/appointment/createMedicine"))
                .andRespond(withSuccess("{\"status\":\"success\"}", MediaType.APPLICATION_JSON));
    }
    @Test
    public void testCreateNewRecord() {
        MedicalRecord medicalRecord = new MedicalRecord(); // 假設已有MedicalRecord類
        // 填充medicalRecord的數據...
//        medicalRecord.setState("init");
        appointmentRestTemplate.createNewRecord(medicalRecord);

        // 驗證Mock Server是否接收到了請求
        server.verify();
        // 進一步的斷言可以根據需要添加，例如檢查返回的實體是否符合預期等
    }

}
