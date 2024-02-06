package nl.nl0e0.appointmentmicroservice.service;

import lombok.Getter;
import nl.nl0e0.appointmentmicroservice.entity.model.AppointmentState;
import nl.nl0e0.appointmentmicroservice.entity.model.BaseRecord;
import nl.nl0e0.appointmentmicroservice.entity.owner.Owner;
import nl.nl0e0.appointmentmicroservice.repository.OwnerRepository;
import nl.nl0e0.appointmentmicroservice.service.MedicalRecordService;
import nl.nl0e0.appointmentmicroservice.entity.*;
import nl.nl0e0.appointmentmicroservice.entity.MedicalRecord;
import nl.nl0e0.appointmentmicroservice.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AppointmentService {

	@Autowired
	AppointmentRecordBuilder appointmentRecordBuilder;
	@Autowired
	MedicalRecordService medicalRecordService;
	@Autowired
	AppointmentRepository appointmentRepository;

	@Autowired
	AppointmentRestTemplate appointmentRestTemplate;
	@Autowired
	OwnerRepository ownerRepository;


	//客戶建立訂單
	public MedicalRecord createAppointment(CreateAppointmentDTO createAppointmentDTO){
		MedicalRecord medicalRecord = medicalRecordService.createMedicalRecord(createAppointmentDTO);
		appointmentRestTemplate.createNewRecord(medicalRecord);
		appointmentRepository.save(new AppointmentEntity(medicalRecord, createAppointmentDTO));
		return medicalRecord;
	}

	public RestTemplate getRestTemplate(){
		return this.appointmentRestTemplate.getRestTemplate();
	}


	public void checkCreateAppointmentDTOValidation(CreateAppointmentDTO createAppointMentDTO){
		String notBeNull = " should not be null.";
		if(createAppointMentDTO.getAppointmentDate() == null)
			throw new NullPointerException("Appointment Date" + notBeNull);
		if(createAppointMentDTO.getPetId() == null)
			throw new NullPointerException("Pet ID" + notBeNull);
		if(createAppointMentDTO.getOwnerId() == null)
			throw new NullPointerException("Owner ID" + notBeNull);
		if(createAppointMentDTO.getVetId() == null)
			throw new NullPointerException("Vet ID" + notBeNull);
	}
	public List<?> getAppointmentsByOwnerId(Integer owner_id){
		List<MedicalRecord> MedicalRecords = medicalRecordService.findByOwnerId(owner_id);
        return getRecordsFromStates(MedicalRecords);
	}

	public List<?> getAppointmentsByOwnerName(OwnerNameDTO ownerNameDTO) {
		Owner owner = ownerRepository.findByFullName(ownerNameDTO.getFirstName(), ownerNameDTO.getLastName());
		List<MedicalRecord> MedicalRecords = medicalRecordService.findByOwnerId(owner.getId());
		return getRecordsFromStates(MedicalRecords);
	}

	private List<BaseRecord> getRecordsFromStates(List<MedicalRecord> medicalRecords){
		return appointmentRecordBuilder.buildRecordsFromMedicalRecords(medicalRecords);
	}

	public void deleteAll() {
		appointmentRepository.deleteAll();
		medicalRecordService.deleteAll();
		appointmentRestTemplate.deleteAllRecord();
	}
//
	public AppointmentEntity findAppointment(String appointmentId) {
		return appointmentRepository.findById(appointmentId);
	}
//
//	public void checkValid(OwnerNameDTO ownerNameDTO) {
//
//	}
//
    public void setState(SetStateDTO setStateDTO) {
		MedicalRecord medicalRecord = medicalRecordService.findByRecordId(setStateDTO.getRecordId());
		if(checkChangeStateAvailable(setStateDTO ,medicalRecord.getState())){
			medicalRecord.setState(setStateDTO.getState());
			medicalRecordService.updateState(medicalRecord);
		}
		else
			throw new RuntimeException("set State denied.");

    }
	public boolean checkChangeStateAvailable(SetStateDTO setStateDTO, String currentState){
		switch (setStateDTO.getState()){
			case "consultation" :
                return currentState.equals("init");
			case "payment":
                return currentState.equals("consultation");
			case "medicine":
				return currentState.equals("payment");
			default:
				return false;
		}
	}
}
