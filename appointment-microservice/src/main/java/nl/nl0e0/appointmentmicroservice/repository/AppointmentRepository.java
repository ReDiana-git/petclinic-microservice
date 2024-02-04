package nl.nl0e0.appointmentmicroservice.repository;

import nl.nl0e0.appointmentmicroservice.entity.AppointmentEntity;
import org.springframework.data.repository.Repository;


public interface AppointmentRepository extends Repository<AppointmentEntity,String> {

	void save(AppointmentEntity appointmentEntity);
	AppointmentEntity findById(String id);
	void deleteAll();


}
