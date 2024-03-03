package nl.nl0e0.consultationmicroservice.service;

import lombok.Getter;
import nl.nl0e0.consultationmicroservice.entity.MedicalRecord;
import nl.nl0e0.consultationmicroservice.entity.SetStateDTO;
import nl.nl0e0.consultationmicroservice.entity.consultation.UpdateConsultationDTO;
import nl.nl0e0.consultationmicroservice.entity.medicine.MedicineEntity;
import nl.nl0e0.consultationmicroservice.entity.medicine.SetMedicineDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Getter
public class ConsultationRestTemplate {

    @Autowired
    RestTemplate restTemplate;

    String appointmentUrl = "http://localhost:8080/appointment";
    String medicineUrl = "http://localhost:8083/appointment";

    public MedicalRecord getRecordById(String recordId){
        ResponseEntity<MedicalRecord> medicalRecordResult = restTemplate.getForEntity(appointmentUrl + "/medicalRecord/" + recordId, MedicalRecord.class);

        return medicalRecordResult.getBody();
    }

    public MedicineEntity getMedicineById(String recordId) {
        ResponseEntity<MedicineEntity> medicineResult = restTemplate.getForEntity(medicineUrl + "/medicine/" + recordId, MedicineEntity.class);

        return medicineResult.getBody();
    }

    public void setState(String recordId, String state){
        SetStateDTO setStateDTO = new SetStateDTO(recordId, state);
        ResponseEntity<?> result = restTemplate.postForEntity(appointmentUrl + "/setState", setStateDTO, String.class);
        if(result.getStatusCode() != HttpStatus.OK)
            throw new RuntimeException("set State failed!");
    }

    public void setMedicine(String medicineId, String medicines) {
        SetMedicineDTO setMedicineDTO = new SetMedicineDTO(medicineId, medicines);
        ResponseEntity<?> result = restTemplate.postForEntity(medicineUrl + "/medicine", setMedicineDTO, String.class);
    }
}
