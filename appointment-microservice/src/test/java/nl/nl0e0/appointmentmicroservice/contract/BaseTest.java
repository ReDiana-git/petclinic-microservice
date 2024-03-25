package nl.nl0e0.appointmentmicroservice.contract;

import io.restassured.config.EncoderConfig;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.config.RestAssuredMockMvcConfig;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
@SpringBootTest(classes = BaseTest.Config.class)
@AutoConfigureMockMvc
public class BaseTest {

    @Autowired WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {

        EncoderConfig encoderConfig = new EncoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false);
        RestAssuredMockMvc.config = new RestAssuredMockMvcConfig().encoderConfig(encoderConfig);
        RestAssuredMockMvc.webAppContextSetup(this.webApplicationContext);

    }
    @Configuration
    @EnableAutoConfiguration
    static class Config{

    }
}
