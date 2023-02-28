package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.AppUser;
import com.example.demo.models.Appointment;

import DTO.AppointmentRequest;
import repositories.AppointmentRepository;
import repositories.UserRepository;

@Service
public class AppointmentsService {

	
	private Long nextAppointmentId = 0L;
	
	//we going to save the appointment in the database so we dont need this array anymore
	//	private ArrayList<Appointment> appointments = new ArrayList<>();
	//inject repository
   private final AppointmentRepository appointmentRepository;
   private final UserRepository userRepository;

	//create constructor
	public AppointmentsService(AppointmentRepository appointmentRepository,UserRepository userRepository) {
		this.appointmentRepository = appointmentRepository;
		this.userRepository = userRepository;
	}
	
	
	public void addNewAppointment(AppointmentRequest appointmentRequest) throws Exception  {
		Optional<AppUser> doctorOpt = userRepository.findById(appointmentRequest.doctorId);
		if(doctorOpt.isEmpty()) {
			throw new Exception();
		}
		//make sure the entity inside the database, AppUser
		AppUser doctor = doctorOpt.get();
		
		Appointment appointment = new Appointment();
		appointment.date = appointmentRequest.date;
		appointment.slot = appointmentRequest.slot;
		appointment.doctor = doctor;//use the doctor variable in line 43
		//we don`t need to fill patientId because every time  doctor create new appointment, patientId is null

		appointmentRepository.save(appointment);
	}
	
	public Iterable<Appointment> getAppointments(Long patientId) throws Exception{
		
		if(patientId != null) {
			
			Optional<AppUser> patientOpt = this.userRepository.findById(patientId);
			if(patientOpt.isEmpty()) {
				throw new Exception();
			}	
			
			Iterable<Appointment> appointmentList = this.appointmentRepository.findAllByPatient(patientOpt.get());
			return appointmentList;
		}
		
		

		return this.appointmentRepository.findAll();
	}
	
	public void deleteById( Long id) throws Exception {

		//check if the id is exist:
		Optional<Appointment> appointmentOpt = this.appointmentRepository.findById(id);
		if(appointmentOpt.isEmpty()) {
			//the id is not in the database
			throw new Exception();
		}
		//if we Here then the id is exist in the database
		this.appointmentRepository.deleteById(id);
	}
	
	public void modifyAppointment(Long id, AppointmentRequest appointmentRequest) throws Exception {

				Optional<Appointment> appointmentOpt = this.appointmentRepository.findById(id);
				if(appointmentOpt.isEmpty()) {
					//the id is not in the database
					throw new Exception();
				}	
				
				Optional<AppUser> patientOpt = this.userRepository.findById(appointmentRequest.patientId);
				if(patientOpt.isEmpty()) {
					throw new Exception();
				}	
				
				Optional<AppUser> doctorOpt = this.userRepository.findById(appointmentRequest.doctorId);
				if(doctorOpt.isEmpty()) {
					throw new Exception();
				}	
				
				
				//if everything exist then update the appointment
				Appointment appointment = appointmentOpt.get();
				//set the appointmentRequest, we are replacing that entity values
				appointment.date = appointmentRequest.date;
				appointment.slot = appointmentRequest.slot;
				appointment.patient = patientOpt.get();
				appointment.doctor = doctorOpt.get();
				
				appointmentRepository.save(appointment);

	}
	
	
	
	


}
