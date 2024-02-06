package nl.nl0e0.appointmentmicroservice.repository;


import nl.nl0e0.appointmentmicroservice.entity.owner.Pet;
import org.springframework.data.repository.Repository;

public interface PetRepository extends Repository<Pet,Integer> {
   Pet findById(Integer id);
}
