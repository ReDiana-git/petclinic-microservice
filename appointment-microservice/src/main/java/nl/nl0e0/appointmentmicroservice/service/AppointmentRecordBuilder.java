package nl.nl0e0.appointmentmicroservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppointmentRecordBuilder {
//    @Autowired
//    ModelSerive modelSerive;
//    @Autowired
//    PaymentService paymentService;
//    @Autowired
//    AppointmentRepository appointmentRepository;
//    public List<BaseRecord> buildRecordsFromMedicalRecords(List<MedicalRecord> medicalRecords) {
//        List<BaseRecord> records = new ArrayList<>();
//        for(MedicalRecord record: medicalRecords){
//            records.add(createBaseRecord(record));
//        }
//        return records;
//    }
//
//    private BaseRecord createBaseRecord(MedicalRecord record) {
//        Owner owner = modelSerive.findOwner(record.getOwnerId());
//        AppointmentEntity appointmentEntity = appointmentRepository.findById(record.getAppointmentId());
//        Vet vet = modelSerive.findVet(record.getVetId());
//        Pet pet = modelSerive.findPet(record.getPetId());
//        return buildBaseRecord(vet, pet, owner, appointmentEntity, record);
//    }
//
//    public BaseRecord buildBaseRecord(Vet vet, Pet pet, Owner owner, AppointmentEntity appointmentEntity, MedicalRecord medicalRecord){
//        BaseRecord baseRecord = new BaseRecord();
//        baseRecord.setVetFirstName(vet.getFirstName());
//        baseRecord.setVetLastName(vet.getLastName());
//        baseRecord.setOwnerFirstName(owner.getFirstName());
//        baseRecord.setOwnerLastName(owner.getLastName());
//        baseRecord.setPetName(pet.getName());
//        baseRecord.setAppointmentDate(appointmentEntity.getAppointmentDate());
//        baseRecord.setState(medicalRecord.getState());
//        if(medicalRecord.getState() == AppointmentState.PAYMENT){
//            baseRecord.setPrice(paymentService.getPayment(medicalRecord.getPaymentId()));
//        }
//        return baseRecord;
//    }
//    private AppointmentState resolveState(AppointmentState state) {
//        return switch (state) {
//            case INIT -> AppointmentState.INIT;
//            case CONSULTAION -> AppointmentState.CONSULTAION;
//            case PAYMENT -> AppointmentState.PAYMENT; // Set additional properties if necessary
//            case MEDICINE -> AppointmentState.MEDICINE;
//            default -> throw new IllegalStateException("Unknown state: " + state);
//        };
//    }

}
