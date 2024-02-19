package nl.nl0e0.consultationmicroservice.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class CreateAppointmentDTO {
	private Integer ownerId;
	private Integer petId;
	private Integer vetId;
	private LocalDateTime appointmentDate;

}
