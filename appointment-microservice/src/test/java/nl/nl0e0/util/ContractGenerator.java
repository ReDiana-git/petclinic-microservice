package nl.nl0e0.util;

import nl.nl0e0.appointmentmicroservice.IntegrationTesting.AppointmentControllerTest;
import nl.nl0e0.appointmentmicroservice.entity.CreateAppointmentDTO;

import java.lang.reflect.Field;

public class ContractGenerator {

    public static void main(String[] args) throws Exception {
        AppointmentControllerTest testInstance = new AppointmentControllerTest();
        Field field = testInstance.getClass().getDeclaredField("testData"); // 获取字段，确保字段名正确
        field.setAccessible(true); // 设置为可访问，即使它是私有的

        CreateAppointmentDTO testData = (CreateAppointmentDTO) field.get(testInstance); // 获取字段值
        System.out.println("Original Vet ID: " + testData.getVetId());

        testData.setVetId(3);
        field.set(testInstance, testData); // 设置修改后的对象
        System.out.println("Modified Vet ID: " + testData.getVetId());
    }
}
