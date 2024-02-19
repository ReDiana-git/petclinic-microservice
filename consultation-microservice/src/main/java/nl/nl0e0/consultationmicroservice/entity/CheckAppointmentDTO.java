package nl.nl0e0.consultationmicroservice.entity;

import lombok.Getter;
import nl.nl0e0.consultationmicroservice.entity.model.BaseRecord;
import nl.nl0e0.consultationmicroservice.entity.owner.Owner;
import nl.nl0e0.consultationmicroservice.entity.vet.Vet;

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
