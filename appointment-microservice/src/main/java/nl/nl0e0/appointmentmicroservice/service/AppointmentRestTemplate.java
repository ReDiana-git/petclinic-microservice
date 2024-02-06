package nl.nl0e0.appointmentmicroservice.service;

import lombok.Getter;
import nl.nl0e0.appointmentmicroservice.entity.MedicalRecord;
import nl.nl0e0.appointmentmicroservice.entity.consultation.ConsultationEntity;
import nl.nl0e0.appointmentmicroservice.entity.medicine.MedicineEntity;
import nl.nl0e0.appointmentmicroservice.entity.payment.PaymentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Getter
public class AppointmentRestTemplate {

    @Autowired
    RestTemplate restTemplate;

    String consultationUrl = "http://localhost:8081/appointment/";
    String paymentUrl = "http://localhost:8082/appointment/";
    String medicineUrl = "http://localhost:8083/appointment/";
    public void createNewRecord(MedicalRecord medicalRecord){
        ResponseEntity<ConsultationEntity> consultationResult = restTemplate.postForEntity(consultationUrl + "createConsultation", medicalRecord, ConsultationEntity.class);
        ResponseEntity<PaymentEntity> paymentResult = restTemplate.postForEntity(paymentUrl + "createPayment", medicalRecord, PaymentEntity.class);
        ResponseEntity<MedicineEntity> medicineResult = restTemplate.postForEntity(medicineUrl + "createMedicine", medicalRecord, MedicineEntity.class);
    }

    public void deleteAllRecord(){
        ResponseEntity<ConsultationEntity> consultationResult = restTemplate.postForEntity(consultationUrl + "deleteConsultation", null, ConsultationEntity.class);
        ResponseEntity<PaymentEntity> paymentResult = restTemplate.postForEntity(paymentUrl + "deletePayment", null, PaymentEntity.class);
        ResponseEntity<MedicineEntity> medicineResult = restTemplate.postForEntity(medicineUrl + "deleteMedicine", null, MedicineEntity.class);
    }
}
