package nl.nl0e0.medicinemicroservice.service;

import nl.nl0e0.medicinemicroservice.entity.MedicalRecord;
import nl.nl0e0.medicinemicroservice.entity.medicine.MedicineCounterDTO;
import nl.nl0e0.medicinemicroservice.entity.medicine.MedicineEntity;
import nl.nl0e0.medicinemicroservice.entity.medicine.SetMedicineDTO;
import nl.nl0e0.medicinemicroservice.repository.MedicineRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MedicineService {
    @Autowired
    MedicineRepositroy repositroy;
    @Autowired
    MedicineRestTemplate restTemplate;


    public void createMedicine(MedicalRecord medicalRecord){
        MedicineEntity medicine = new MedicineEntity(medicalRecord);
        repositroy.save(medicine);
    }
    public void updateMedicine(String MedicineId, String medicines){
        repositroy.updateMedicines(medicines, MedicineId);
    }

    public MedicineEntity findRecordById(String recordId){
        return repositroy.findById(recordId);
    }

    public void deleteAll(){
        repositroy.deleteAll();
    }

    public MedicineCounterDTO medicineCounter(String recordId) throws IllegalAccessException {
        MedicalRecord medicalRecord = restTemplate.getRecordById(recordId);
//        if(!(medicalRecord.getState2String().equals("medicine")))
//            throw new IllegalAccessException("You are not at medicine state.");
//
//        medicalRecord.setState("done");
        restTemplate.setState(recordId, "done");

        return new MedicineCounterDTO(recordId, "done");
    }

    public MedicineEntity findMedicineByRecordId(String recordId) {
        MedicalRecord medicalRecord = restTemplate.getRecordById(recordId);
        MedicineEntity medicineEntity = repositroy.findById(medicalRecord.getMedicineId());
        return medicineEntity;
    }

    public void setMedicine(SetMedicineDTO setMedicineDTO) {
        MedicalRecord medicalRecord = restTemplate.getRecordById(setMedicineDTO.getRecordId());
//        repositroy.updateMedicines();
    }
}
