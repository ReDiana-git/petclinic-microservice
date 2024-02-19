package nl.nl0e0.consultationmicroservice.entity.medicine;

public class SetMedicineDTO {
    String recordId;
    String state;
    public SetMedicineDTO(String recordId, String state){
        this.recordId = recordId;
        this.state = state;
    }
}
