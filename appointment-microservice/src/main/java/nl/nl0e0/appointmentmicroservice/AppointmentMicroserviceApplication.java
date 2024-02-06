package nl.nl0e0.appointmentmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(nl.nl0e0.appointmentmicroservice.configuration.RestTemplateConfig.class)
public class AppointmentMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppointmentMicroserviceApplication.class, args);
    }

}
