package nl.nl0e0.medicinemicroservice.contract;

import io.restassured.config.EncoderConfig;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.config.RestAssuredMockMvcConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.WebApplicationContext;

@AutoConfigureStubRunner(
        ids = {
                "nl.nl0e0:consultation-microservice:+:stubs:8081",
                "nl.nl0e0:payment-microservice:+:stubs:8082",
                "nl.nl0e0:appointment-microservice:+:stubs:8080"
        },
        stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
@SpringBootTest(classes = nl.nl0e0.medicinemicroservice.MedicineMicroserviceApplication.class)
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
                "INSERT  INTO medicine VALUES ('2536026b-4b7b-4d1b-86c3-72214e952a23', '')"
        );
    }
    @AfterEach
    public void close(){
        jdbcTemplate.update("DELETE FROM medicine");
    }
}
