package nl.nl0e0.appointmentmicroservice.service;

import nl.nl0e0.appointmentmicroservice.entity.MedicalRecord;
import nl.nl0e0.appointmentmicroservice.entity.consultation.ConsultationEntity;
import nl.nl0e0.appointmentmicroservice.entity.medicine.MedicineEntity;
import nl.nl0e0.appointmentmicroservice.entity.payment.PaymentEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AppointmentRestTemplate {
    RestTemplate restTemplate = new RestTemplate();
    String consultationUrl = "http://localhost:8081/appointment/createConsultation";
    String paymentUrl = "http://localhost:8082/appointment/createPayment";
    String medicineUrl = "http://localhost:8083/appointment/createMedicine";
    public void createNewRecord(MedicalRecord medicalRecord){
        ResponseEntity<ConsultationEntity> consultationResult = restTemplate.postForEntity(consultationUrl, medicalRecord, ConsultationEntity.class);
        ResponseEntity<PaymentEntity> paymentResult = restTemplate.postForEntity(paymentUrl, medicalRecord, PaymentEntity.class);
        ResponseEntity<MedicineEntity> medicineResult = restTemplate.postForEntity(medicineUrl, medicalRecord, MedicineEntity.class);
    }
}
