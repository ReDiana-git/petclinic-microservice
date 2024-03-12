package nl.nl0e0.appointmentmicroservice.controller;

import nl.nl0e0.appointmentmicroservice.entity.SetStateDTO;
import nl.nl0e0.appointmentmicroservice.service.AppointmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = AppointmentController.class)
public class AppointmentControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService appointmentService;

    @InjectMocks
    private AppointmentController appointmentController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(appointmentController).build();
    }

    @Test
    public void testSetState() throws Exception {
        SetStateDTO setStateDTO = new SetStateDTO(); // 替換成您的 DTO 初始化
        // 根據您的測試情境設定 DTO 的屬性

        doNothing().when(appointmentService).setState(setStateDTO);

        mockMvc.perform(post("/appointment/setState")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(setStateDTO)))
                .andExpect(status().isOk());
    }

//    @Test
//    void setStateFailure() throws Exception {
//        SetStateDTO setStateDTO = new SetStateDTO(); // 替換成您的 DTO 初始化
//        // 根據您的測試情境設定 DTO 的屬性
//
//        // 正確的模擬方式
//        doThrow(new RuntimeException("Custom error message")).when(appointmentService).setState(setStateDTO);
//
//        mockMvc.perform(post("/appointment/setState")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(setStateDTO)))
//                .andExpect(result -> {
//                    // 這裡可以加入更多對異常響應的檢查，比如檢查響應體的內容
//                    assert result.getResolvedException() instanceof RuntimeException;
//                    assert "Custom error message".equals(result.getResolvedException().getMessage());
//                })
//                .andExpect(status().isBadRequest());
//    }

}
