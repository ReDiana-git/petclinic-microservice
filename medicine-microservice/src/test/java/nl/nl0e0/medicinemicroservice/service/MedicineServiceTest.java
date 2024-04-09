package nl.nl0e0.medicinemicroservice.service;

import nl.nl0e0.medicinemicroservice.entity.medicine.MedicineEntity;
import nl.nl0e0.medicinemicroservice.repository.MedicineRepositroy;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MedicineServiceTest {

    @Autowired
    private MedicineService medicineService;
    @InjectMocks
    private MedicineService mockMedicineService;
    private MockRestServiceServer server;
    @Mock
    private MedicineRepositroy medicineRepository;

    @Autowired
    private MedicineRepositroy repositroy;


    @Value("${TEST_RECORD_ID:ac7c665d-e606-4b73-a938-51e37decaa82}")
    private String recordId;

    String recordBody = "{\n" +
            "    \"id\": \"ac7c665d-e606-4b73-a938-51e37decaa82\",\n" +
            "    \"ownerId\": 1,\n" +
            "    \"vetId\": 1,\n" +
            "    \"state\": \"consultation\",\n" +
            "    \"petId\": 2,\n" +
            "    \"appointmentId\": \"da1be146-44fa-4ed1-8127-f09f540fc9bf\",\n" +
            "    \"consultationId\": \"0762f522-b9b8-4bb3-940c-0e124acb9970\",\n" +
            "    \"paymentId\": \"24d85502-e937-4aa3-ae55-74f2b4d24fd2\",\n" +
            "    \"medicineId\": \"eedc2cb4-43fc-4214-b178-392bc8e3e689\",\n" +
            "    \"state2String\": \"consultation\"\n" +
            "}";

//    @Test
    public void testCreateMedicine(){
        MedicineEntity medicineEntity = new MedicineEntity();
        medicineEntity.setId(UUID.randomUUID().toString());
        mockMedicineService.createMedicine(UUID.randomUUID().toString());

        MedicineEntity medicine = new MedicineEntity();
        medicine.setId(recordId);

        // 验证repository.save是否被正确调用，并且是用了正确的MedicineEntity对象
        verify(medicineRepository, times(1)).save(any(MedicineEntity.class));
        medicineRepository.deleteMedicineEntitiesById(medicineEntity.getId());
    }

//    @Test
    public void testmedicineCounter() throws IllegalAccessException {

        this.server = MockRestServiceServer.createServer(medicineService.restTemplate.getRestTemplate());
        server.expect(requestTo("http://localhost:8080/appointment/medicalRecord/" + recordId))
                .andRespond(withSuccess(recordBody, MediaType.APPLICATION_JSON));
        server.expect(requestTo("http://localhost:8080/appointment/setState"))
                .andRespond(withSuccess("{\"status\":\"success\"}", MediaType.APPLICATION_JSON));
        medicineService.medicineCounter(recordId);
        server.verify();
        server = null;
    }

//    @Test
    public void testFindMedicineByRecordId(){
        this.server = MockRestServiceServer.createServer(medicineService.restTemplate.getRestTemplate());
        server.expect(requestTo("http://localhost:8080/appointment/medicalRecord/" + recordId))
                .andRespond(withSuccess(recordBody, MediaType.APPLICATION_JSON));
        MedicineEntity medicine = medicineService.findMedicineByRecordId(recordId);
        System.out.println(medicine.getMedicines());
        server.verify();
//        MedicineEntity result = repositroy.findById("eedc2cb4-43fc-4214-b178-392bc8e3e689");
//        System.out.println(result.getMedicines());
//        assertThat(medicine).isEqualTo(result);
    }


}
