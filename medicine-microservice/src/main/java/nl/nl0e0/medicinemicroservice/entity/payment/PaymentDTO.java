package nl.nl0e0.medicinemicroservice.entity.payment;

import lombok.Getter;

@Getter
public class PaymentDTO {
    String recordId;
    Integer price;
    String cardNumber;
    String cardFirstName;
    String cardLastName;
    String checkNum;
}
