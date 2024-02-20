package nl.nl0e0.medicinemicroservice.entity.medicine;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SetMedicineDTO {
    String recordId;
    String state;
    public SetMedicineDTO(String recordId, String state){
        this.recordId = recordId;
        this.state = state;
    }
}
