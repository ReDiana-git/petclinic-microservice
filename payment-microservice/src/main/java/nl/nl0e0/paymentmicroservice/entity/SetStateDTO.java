package nl.nl0e0.paymentmicroservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class SetStateDTO {
    @Id
    String recordId;
    String state;
    public SetStateDTO(String recordId, String state){
        this.recordId = recordId;
        this.state = state;
    }
    public SetStateDTO(){

    }
}
