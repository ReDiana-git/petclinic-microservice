package nl.nl0e0.consultationmicroservice.service;

import nl.nl0e0.consultationmicroservice.entity.SetStateDTO;
import nl.nl0e0.consultationmicroservice.entity.consultation.CheckConsultationDTO;
import nl.nl0e0.consultationmicroservice.entity.consultation.ConsultationEntity;
import nl.nl0e0.consultationmicroservice.entity.consultation.UpdateConsultationDTO;
import nl.nl0e0.consultationmicroservice.entity.MedicalRecord;
import nl.nl0e0.consultationmicroservice.entity.medicine.MedicineEntity;
import nl.nl0e0.consultationmicroservice.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultationService {
    @Autowired
    ConsultationRepository consultationRepository;
    ConsultationRestTemplate consultationRestTemplate;

    public void createConsultation(MedicalRecord medicalRecord){
        ConsultationEntity consultationEntity = new ConsultationEntity(medicalRecord);
        consultationRepository.save(consultationEntity);
    }




    public void deleteAll() {
        consultationRepository.deleteAll();
    }

    public CheckConsultationDTO checkConsultation(String recordId) {
        MedicalRecord record = consultationRestTemplate.getRecordById(recordId);
        ConsultationEntity consultationEntity = consultationRepository.findById(record.getConsultationId());
        MedicineEntity medicineEntity = consultationRestTemplate.getMedicineById(record.getMedicineId());
        consultationRestTemplate.setState(recordId, "consultation");
        return new CheckConsultationDTO(consultationEntity.getSymptom(),
                medicineEntity.getMedicines(),
                record.getOwnerId(),
                record.getPetId(),
                record.getState());
    }

    public void updateConsultation(UpdateConsultationDTO updateConsultationDTO) {
        MedicalRecord medicalRecord = consultationRestTemplate.getRecordById(updateConsultationDTO.getRecordId());
        consultationRestTemplate.setMedicine(medicalRecord.getMedicineId(),updateConsultationDTO.getMedicines());
        consultationRepository.updateSymptom(medicalRecord.getConsultationId(), updateConsultationDTO.getSymptom());
        consultationRestTemplate.setState(medicalRecord.getId(), "payment");
    }

}
