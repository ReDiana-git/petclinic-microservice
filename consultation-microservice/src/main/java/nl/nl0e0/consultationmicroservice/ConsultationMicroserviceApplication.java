package nl.nl0e0.consultationmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(nl.nl0e0.consultationmicroservice.configuration.RestTemplateConfig.class)
public class ConsultationMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsultationMicroserviceApplication.class, args);
    }

}
