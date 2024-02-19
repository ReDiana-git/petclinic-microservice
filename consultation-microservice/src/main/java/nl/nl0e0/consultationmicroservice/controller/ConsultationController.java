package nl.nl0e0.consultationmicroservice.controller;

import lombok.Getter;
import nl.nl0e0.consultationmicroservice.entity.consultation.CheckConsultationDTO;
import nl.nl0e0.consultationmicroservice.entity.consultation.UpdateConsultationDTO;
import nl.nl0e0.consultationmicroservice.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ConsultationController {

    @Autowired
    ConsultationService consultationService;

    //給醫生查看病歷
    @GetMapping("/appointment/consultation/{id}")
    public ResponseEntity<?> checkConsultation(@PathVariable String recordId){
        CheckConsultationDTO checkConsultationDTO = consultationService.checkConsultation(recordId);
        return ResponseEntity.status(HttpStatus.OK).body(checkConsultationDTO);
    }

    //給醫生更新病歷
    @PostMapping("/appoint/updateConsultation")
    public ResponseEntity<?> updateConsultation(@RequestBody UpdateConsultationDTO updateConsultationDTO){
        System.out.println("updateConsultationDTO Object in Controller\n" + updateConsultationDTO);
        consultationService.updateConsultation(updateConsultationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
