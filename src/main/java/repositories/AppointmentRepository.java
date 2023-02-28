package repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.AppUser;
import com.example.demo.models.Appointment;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long>{
	//while there is no patientId 
	//we will find the patient which type AppUser
	Iterable<Appointment> findAllByPatient(AppUser user);
	

}
