package nl.nl0e0.appointmentmicroservice.entity.medicine;

public class MedicineCounterDTO {
    String recordId;
    String state;
    public MedicineCounterDTO(String recordId, String state){
        this.recordId = recordId;
        this.state = state;
    }
}
