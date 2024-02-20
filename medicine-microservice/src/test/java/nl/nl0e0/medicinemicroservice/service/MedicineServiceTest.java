package nl.nl0e0.medicinemicroservice.service;

import nl.nl0e0.medicinemicroservice.repository.MedicineRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.client.MockRestServiceServer;

@SpringBootTest
public class MedicineServiceTest {
    @Autowired
    private MedicineService medicineService;
    private MockRestServiceServer server;
    @Autowired
    private MedicineRepositroy medicineRepositroy;

    String recordBody = "{\n" +
            "    \"id\": \"317498db-9841-408a-b01d-6f7d4d8da4fc\",\n" +
            "    \"ownerId\": 1,\n" +
            "    \"vetId\": 1,\n" +
            "    \"state\": \"consultation\",\n" +
            "    \"petId\": 2,\n" +
            "    \"appointmentId\": \"2056e53e-72b7-4110-9afb-b219d2907dec\",\n" +
            "    \"consultationId\": \"70c3ad0c-547e-458f-901b-76c1a9dce9be\",\n" +
            "    \"paymentId\": \"2536026b-4b7b-4d1b-86c3-72214e952a23\",\n" +
            "    \"medicineId\": \"c3f865b1-4b3c-4c0a-9bc2-7c703f2ba99e\",\n" +
            "    \"state2String\": \"consultation\"\n" +
            "}";
}
