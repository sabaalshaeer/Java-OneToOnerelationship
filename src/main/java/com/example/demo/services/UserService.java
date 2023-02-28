package com.example.demo.services;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.AppUser;
import com.example.demo.models.Appointment;
import com.example.demo.repositories.UserRespository;

import repositories.AppointmentRepository;
import repositories.UserRepository;


// Services in Spring:
// dedicated to handling logic
// available to be injected anywhere
// as pure Java as possible ( no much of annotation which is dense on Spring)
@Service
public class UserService {
	
	//inject UserRespository
	private final UserRepository repository;
	private final AppointmentRepository appointmentRepository;
	
	
	public UserService(UserRepository repository, AppointmentRepository appointmentRepository ) {
		this.repository = repository;
		this.appointmentRepository = appointmentRepository;
	}

	
	public AppUser getByUsernameAndPassword(String username, String password ) {
		//Optional in Java : a easier way to write null safe code
		 return repository
				 .findAppUserByUsernameAndPassword(username, password)
				 .orElse(null); // SQL: Select form app-user where username = '' and password = ''		
	}
	
	public void register(AppUser appUser) throws Exception {
		if(repository.findAppUserByUsernameAndPassword(appUser.username).isPresent())
				throw new Exception();

		repository.save(appUser);//save came from CrudRepository
	}
	
/*
 * this appointment should be exist in the backend
 * how to call this method in FE:
 * this.http.put('http://localhost:8080/users/${id}`, appointment)
 * @param id
 * @param appointment
 */
	
	
	//it is example to clarify who to connect the appointment to the user
	public void addAvailability(Long doctorId, Appointment appointment ) {
		//I assume the doctor and the appointment exists inside of the database
		final var doctor = repository.findById(doctorId).orElseThrow();
		final var appointmentToAdd = appointmentRepository.findById(appointment.id).orElseThrow();
		doctor.appointments.add(appointment);//that particular obj will now  have that appointment, now have added to it.
		//all of this only in memory but not persist in the database yet
				
		//now I modify the appUser and now I need to tell app user repository to save that
		repository.save(doctor);
		
		//this will modify the app-user-appointments table , which is the linked table
	}
}













