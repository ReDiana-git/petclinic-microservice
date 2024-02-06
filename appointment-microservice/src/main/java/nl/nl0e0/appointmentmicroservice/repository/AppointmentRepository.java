package nl.nl0e0.appointmentmicroservice.repository;

import nl.nl0e0.appointmentmicroservice.entity.AppointmentEntity;
import org.springframework.data.repository.Repository;

import java.util.List;


public interface AppointmentRepository extends Repository<AppointmentEntity,String> {

	void save(AppointmentEntity appointmentEntity);
	AppointmentEntity findById(String id);
	List<AppointmentEntity> findAll();
	void deleteAll();


}
