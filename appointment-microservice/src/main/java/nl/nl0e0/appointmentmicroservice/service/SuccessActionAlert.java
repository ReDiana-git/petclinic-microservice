package nl.nl0e0.appointmentmicroservice.service;


import nl.nl0e0.appointmentmicroservice.entity.MedicalRecord;

public class SuccessActionAlert {
    public void createAppointmentAlert(MedicalRecord medicalRecord){
        System.out.println("-----------------");
        System.out.println("Create Appointment Success.");
        System.out.println(medicalRecord);
        System.out.println("-----------------");
    }
    public void deleteAppointmentsAlert(){
        System.out.println("-----------------");
        System.out.println("All Data in Database Has Been Deleted.");
        System.out.println("-----------------");
    }
}
