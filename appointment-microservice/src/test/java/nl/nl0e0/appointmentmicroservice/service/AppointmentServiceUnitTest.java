package nl.nl0e0.appointmentmicroservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import nl.nl0e0.appointmentmicroservice.entity.MedicalRecord;
import nl.nl0e0.appointmentmicroservice.entity.SetStateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AppointmentServiceUnitTest {

    @Mock
    private MedicalRecordService medicalRecordService;

    @InjectMocks
    private AppointmentService appointmentService; // 假設setState方法在此服務中

    private SetStateDTO setStateDTO;
    private MedicalRecord medicalRecord;

    @BeforeEach
    void setUp() {
        setStateDTO = new SetStateDTO();
        setStateDTO.setRecordId(""); // 假設的ID
        setStateDTO.setState("consultation"); // 假設的新狀態

        medicalRecord = new MedicalRecord();
        medicalRecord.setState("init"); // 初始狀態
    }

    @Test
    void setStateSuccess() {
        when(medicalRecordService.findByRecordId(setStateDTO.getRecordId())).thenReturn(medicalRecord);
        doNothing().when(medicalRecordService).updateState(any(MedicalRecord.class));

        assertDoesNotThrow(() -> appointmentService.setState(setStateDTO));
        assertEquals("consultation", medicalRecord.getState()); // 驗證狀態是否更新
    }

    @Test
    void setStateFailure() {

        setStateDTO.setState("done");
        // 使查找方法返回一個醫療記錄，但假設狀態不允許更改
        when(medicalRecordService.findByRecordId(setStateDTO.getRecordId())).thenReturn(medicalRecord);
        // 當checkChangeStateAvailable返回false時，不需要直接模擬這個方法，因為它是私有的或者是這個測試不關心的實現細節
        // 只需確保你的測試條件模擬一個不允許更改的情況即可，例如通過設置一個不允許更改的初始狀態

        Exception exception = assertThrows(RuntimeException.class, () -> appointmentService.setState(setStateDTO));
        assertEquals("set State denied.", exception.getMessage());
    }
    @Test
    public void testChangeStateInit2Consultation(){
        SetStateDTO setStateDTO = new SetStateDTO("", "consultation");
        boolean result = appointmentService.checkChangeStateAvailable(setStateDTO, "init");
        assertThat(result).isEqualTo(true);
    }
}
