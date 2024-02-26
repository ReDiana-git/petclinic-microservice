package nl.nl0e0.paymentmicroservice.service;

import nl.nl0e0.paymentmicroservice.entity.SetStateDTO;
import nl.nl0e0.paymentmicroservice.entity.MedicalRecord;
import nl.nl0e0.paymentmicroservice.entity.model.AppointmentState;
import nl.nl0e0.paymentmicroservice.entity.payment.*;
import nl.nl0e0.paymentmicroservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository repository;

    PaymentRestTemplate restTemplate;

    public void createPayment(String recordId){
        PaymentEntity payment = new PaymentEntity();
        payment.setId(recordId);
        repository.save(payment);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Integer getPayment(String paymentId) {
        return repository.findById(paymentId).getPrice();
    }
    public PaymentInfoDTO getPaymentInfo(String recordId){
//        MedicalRecord medicalRecord = medicalRecordService.findByRecordId(recordId);
        MedicalRecord medicalRecord = restTemplate.getRecordById(recordId);
        PaymentEntity paymentEntity = repository.findById(medicalRecord.getPaymentId());
        PaymentInfoDTO paymentInfoDTO = new PaymentInfoDTO(recordId, paymentEntity.getPrice(), medicalRecord.getState2String());
        return paymentInfoDTO;
    }
    public PaymentSucessDTO payment(PaymentDTO paymentDTO){
        CardEntity cardEntity = new CardEntity(paymentDTO.getCardNumber(), paymentDTO.getCardFirstName(), paymentDTO.getCardLastName(), paymentDTO.getCheckNum());
        if(!checkCreditCardWithLuhnAlgor(cardEntity))
            throw new IllegalArgumentException("The Card Number is not available!");
        restTemplate.setState(paymentDTO.getRecordId(), "medicine");
        MedicalRecord medicalRecord = restTemplate.getRecordById(paymentDTO.getRecordId());
        return new PaymentSucessDTO(medicalRecord.getPaymentId(), medicalRecord.getState2String());
    }
    public boolean checkCreditCardWithLuhnAlgor(CardEntity cardEntity){
        String cardNum = cardEntity.getCardNumber();
        if(cardNum.length() != 16){
            System.out.println("The Card Num is " + cardNum.length() +" long.");
            return false;
        }
        int checkNumSum;
        if((cardNum.charAt(0) - '0') * 2 > 9)
            checkNumSum = (cardNum.charAt(0) - '0') * 2 % 10 + (cardNum.charAt(0) - '0') * 2 / 10;
        else
            checkNumSum = (cardNum.charAt(0) - '0') * 2;
        for(int i = 1; i < 15; i+=2){
            checkNumSum += cardNum.charAt(i) - '0';
            int tmp = (cardNum.charAt(i + 1) -'0') * 2;
            if(tmp > 9)
                checkNumSum = checkNumSum + tmp % 10 + tmp / 10;
            else
                checkNumSum += tmp;
        }
        return (cardNum.charAt(15) - '0') == (10 - checkNumSum % 10);
    }

    public boolean checkChangeStateAvailable(SetStateDTO setStateDTO, AppointmentState currentState){
        switch (setStateDTO.getState()){
            case "consultation" :
                return currentState.equals(AppointmentState.INIT);
            case "payment":
                return currentState.equals(AppointmentState.CONSULTAION);
            case "medicine":
                return currentState.equals(AppointmentState.PAYMENT);
            default:
                return false;
        }
    }
}
