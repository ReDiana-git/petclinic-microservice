package nl.nl0e0.consultationmicroservice.contract;

import io.restassured.config.EncoderConfig;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.config.RestAssuredMockMvcConfig;
import nl.nl0e0.consultationmicroservice.controller.ConsultationController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.WebApplicationContext;

@AutoConfigureStubRunner(
        ids = {
                "nl.nl0e0:appointment-microservice:+:stubs:8080",
                "nl.nl0e0:payment-microservice:+:stubs:8082",
                "nl.nl0e0:medicine-microservice:+:stubs:8083"
        },
        stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
@SpringBootTest(classes = nl.nl0e0.consultationmicroservice.ConsultationMicroserviceApplication.class)
public class BaseTest {
    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setup() {

        EncoderConfig encoderConfig = new EncoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false);
        RestAssuredMockMvc.config = new RestAssuredMockMvcConfig().encoderConfig(encoderConfig);
        RestAssuredMockMvc.webAppContextSetup(this.webApplicationContext);

        jdbcTemplate.update(
                "INSERT INTO consultation VALUES ('70c3ad0c-547e-458f-901b-76c1a9dce9be', 'cough')"
        );
    }
    @AfterEach
    public void close(){
        jdbcTemplate.update("DELETE FROM consultation");
    }
}
