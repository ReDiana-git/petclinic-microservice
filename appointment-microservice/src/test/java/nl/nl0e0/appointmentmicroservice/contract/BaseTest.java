package nl.nl0e0.appointmentmicroservice.contract;

import io.restassured.config.EncoderConfig;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.config.RestAssuredMockMvcConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
@AutoConfigureStubRunner(
        ids = {
                "nl.nl0e0:consultation-microservice:+:stubs:8081",
                "nl.nl0e0:payment-microservice:+:stubs:8082",
                "nl.nl0e0:medicine-microservice:+:stubs:8083"
        },
        stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
@SpringBootTest(classes = nl.nl0e0.appointmentmicroservice.AppointmentMicroserviceApplication.class)
public class BaseTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @BeforeEach
    public void setup(TestInfo testInfo) {

        EncoderConfig encoderConfig = new EncoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false);
        RestAssuredMockMvc.config = new RestAssuredMockMvcConfig().encoderConfig(encoderConfig);
        RestAssuredMockMvc.webAppContextSetup(this.webApplicationContext);
        if(testInfo.getTestMethod().toString().contains("setState")){
            jdbcTemplate.update(
                    "INSERT INTO medicalrecord VALUES ('317498db-9841-408a-b01d-6f7d4d8da4fc', 1, 2, 'consultation', '2056e53e-72b7-4110-9afb-b219d2907dec', '70c3ad0c-547e-458f-901b-76c1a9dce9be', '2536026b-4b7b-4d1b-86c3-72214e952a23', 'c3f865b1-4b3c-4c0a-9bc2-7c703f2ba99e', 1)"
            );
        }
        else{
            jdbcTemplate.update(
                    "INSERT INTO medicalrecord VALUES ('317498db-9841-408a-b01d-6f7d4d8da4fc', 1, 2, 'init', '2056e53e-72b7-4110-9afb-b219d2907dec', '70c3ad0c-547e-458f-901b-76c1a9dce9be', '2536026b-4b7b-4d1b-86c3-72214e952a23', 'c3f865b1-4b3c-4c0a-9bc2-7c703f2ba99e', 1)"
            );
        }
        jdbcTemplate.update(
                "INSERT  INTO appointments VALUES ('2056e53e-72b7-4110-9afb-b219d2907dec', '2024-03-05T21:00:00', '2024-03-05T21:00:00')"
        );
    }

    @AfterEach
    public void close(){
        jdbcTemplate.update("DELETE FROM medicalrecord");
        jdbcTemplate.update("DELETE  FROM appointments");
    }
}
