package nl.nl0e0.appointmentmicroservice.service;

import nl.nl0e0.appointmentmicroservice.entity.*;
import nl.nl0e0.appointmentmicroservice.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {
    @Autowired
    MedicalRecordRepository repository;
    public MedicalRecord createMedicalRecord(CreateAppointmentDTO createAppointmentDTO){
        MedicalRecord medicalRecord = new MedicalRecord(createAppointmentDTO);
        repository.save(medicalRecord);
        return medicalRecord;
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public List<MedicalRecord> findByOwnerId(Integer ownerId) {
        return repository.findByOwnerId(ownerId);
    }

    public MedicalRecord findByRecordId(String recordId){
        return repository.findById(recordId);
    }

    public void updateState(MedicalRecord medicalRecord) {
        repository.updateState(medicalRecord.getState2String(), medicalRecord.getId());
    }
}
