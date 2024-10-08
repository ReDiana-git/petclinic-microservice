package nl.nl0e0.appointmentmicroservice.entity.consultation;

import lombok.Getter;
import nl.nl0e0.appointmentmicroservice.entity.model.AppointmentState;


@Getter
public class CheckConsultationDTO{
	// 客戶查看病狀態
	// 會回覆客戶此病歷的金額
	private String symptom;
	private String medicines;
	private Integer ownerId;
	private Integer petId;
	private AppointmentState state;

	public CheckConsultationDTO(String symptom, String medicines, Integer ownerId, Integer petId, AppointmentState state){
		this.symptom = symptom;
		this.medicines = medicines;
		this.ownerId = ownerId;
		this.petId = petId;
		this.state = state;
	}
}
