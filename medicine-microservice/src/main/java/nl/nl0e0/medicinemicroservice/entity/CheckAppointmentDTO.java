package nl.nl0e0.medicinemicroservice.entity;

import lombok.Getter;
import nl.nl0e0.medicinemicroservice.entity.model.BaseRecord;
import nl.nl0e0.medicinemicroservice.entity.owner.Owner;
import nl.nl0e0.medicinemicroservice.entity.vet.Vet;

import java.time.LocalDateTime;

@Getter
public class CheckAppointmentDTO extends BaseRecord {

	private LocalDateTime appointmentDate;



	public CheckAppointmentDTO(AppointmentEntity appointmentEntity, MedicalRecord record, Owner owner, Vet vet){
		appointmentDate = appointmentEntity.getAppointmentDate();
		setOwnerFirstName(owner.getFirstName());
		setOwnerLastName(owner.getLastName());
		setVetFirstName(vet.getFirstName());
		setVetLastName(getVetLastName());
		setState(record.getState());
	}
}
