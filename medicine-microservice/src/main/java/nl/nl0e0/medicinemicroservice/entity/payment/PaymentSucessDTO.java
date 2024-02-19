package nl.nl0e0.medicinemicroservice.entity.payment;

public class PaymentSucessDTO {
    String recordId;
    String status;
    public PaymentSucessDTO(String recordId, String status){
        this.recordId = recordId;
        this.status = status;
    }
}
