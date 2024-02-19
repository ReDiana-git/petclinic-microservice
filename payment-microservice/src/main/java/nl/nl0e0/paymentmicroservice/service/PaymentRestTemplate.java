package nl.nl0e0.paymentmicroservice.service;

import lombok.Getter;
import nl.nl0e0.paymentmicroservice.entity.MedicalRecord;
import nl.nl0e0.paymentmicroservice.entity.SetStateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Getter
public class PaymentRestTemplate {

    String appointmentUrl = "http://localhost:8084/appointment";

    @Autowired
    RestTemplate restTemplate;

    public MedicalRecord getRecordById(String recordId) {
        ResponseEntity<MedicalRecord> medicalRecordResult = restTemplate.getForEntity(appointmentUrl + "/medicalRecord/" + recordId, MedicalRecord.class);

        return medicalRecordResult.getBody();
    }
    public void setState(String recordId, String state) {
        SetStateDTO setStateDTO = new SetStateDTO(recordId, state);
        ResponseEntity<?> result = restTemplate.postForEntity(appointmentUrl + "/setState", setStateDTO, String.class);
        if(result.getStatusCode() != HttpStatus.OK)
            throw new RuntimeException("set State failed!");
    }
}
