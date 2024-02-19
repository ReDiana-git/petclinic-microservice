package nl.nl0e0.medicinemicroservice.entity.medicine;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import nl.nl0e0.medicinemicroservice.entity.MedicalRecord;

import java.io.Serializable;

@Getter
@Entity
@Table(name = "medicine")
public class MedicineEntity implements Serializable {
	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "medicines")
	private String medicines;

	public MedicineEntity() {

	}


	public MedicineEntity(MedicalRecord medicalRecord){
		this.id = medicalRecord.getMedicineId();
	}
}
