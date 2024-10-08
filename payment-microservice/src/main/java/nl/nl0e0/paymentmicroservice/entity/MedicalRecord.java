package nl.nl0e0.paymentmicroservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import nl.nl0e0.paymentmicroservice.entity.model.AppointmentState;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Entity
@Table(name = "medicalrecord")
public class MedicalRecord implements Serializable {

	@Id
	@Column(name = "id")
	private String id = UUID.randomUUID().toString();

	@Column(name = "owner_id")
	private Integer ownerId;

	@Column(name = "vet_id")
	private Integer vetId;
//	@Transient
//	@JsonIgnore
	@Column(name = "state")
	private String state;

	@Column(name = "pet_id")
	private Integer petId;
	@Column(name = "appointment_id")
	private String appointmentId = UUID.randomUUID().toString();
	@Column(name = "consultation_id")
	private String consultationId = UUID.randomUUID().toString();
	@Column(name = "payment_id")
	private String paymentId = UUID.randomUUID().toString();
	@Column(name = "medicine_id")
	private String medicineId = UUID.randomUUID().toString();


//	public AppointmentState getStateWithState(){
//		switch(state){
//			case "init":
//				return AppointmentState.INIT;
//			case "consultation":
//				return AppointmentState.CONSULTAION;
//			case "payment":
//				return AppointmentState.PAYMENT;
//			case "medicine":
//				return AppointmentState.MEDICINE;
//			case "done":
//				return AppointmentState.DONE;
//		}
//		return null;
//	}

	public String getState2String(){
		return state;
	}
//	public void setState(AppointmentState state){
//		switch (state){
//			case INIT -> this.state = "init";
//			case PAYMENT -> this.state = "payment";
//			case CONSULTAION -> this.state = "consultation";
//			case MEDICINE -> this.state = "medicine";
//			case DONE -> this.state = "done";
//		}
//	}
//	public boolean setState(String state){
//		if(checkChangeStateAvailable(state) || state.equals(this.state)){
//			this.state = state;
//			return true;
//		}
//		else{
//			return false;
//		}
//
//
//	}
//	public boolean checkChangeStateAvailable(String setState){
//		switch (setState){
//			case "consultation" :
//				return this.state.equals("init");
//			case "payment":
//				return this.state.equals("consultation");
//			case "medicine":
//				return this.state.equals("payment");
//			case "done":
//				return this.state.equals("medicine");
//			default:
//				return false;
//		}
//	}
//
//	public MedicalRecord(CreateAppointmentDTO dto) {
//		this.ownerId = dto.getOwnerId();
//		this.petId = dto.getPetId();
//		this.vetId = dto.getVetId();
//		this.state = "init";
//	}

	public MedicalRecord(){

	}
	@Override
	public String toString(){
		return "Record ID: " + id
				+ "\nAppointment ID: " + appointmentId
				+ "\nConsultation ID: " + consultationId
				+ "\nPayment ID: " + consultationId
				+ "\nMedicine ID: " + medicineId ;
	}
}
