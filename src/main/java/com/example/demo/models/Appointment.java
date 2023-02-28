package com.example.demo.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//definition of Appointment
import jakarta.persistence.OneToOne;

//The JPA specification defines a set of standard annotations, such as @Entity, @Id, @GeneratedValue, etc.,
//which are used to map Java objects to database tables and define the relationships between them.
@Entity
public class Appointment {
	@Id //specific the id as primary key
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	
	public Long id;
	public Date date;
	public int slot;
	
	@OneToOne
	public AppUser patient; // it is entity
	
	@OneToOne
	public AppUser doctor; // it is entity

	
	
	//in FE we need to change the property of patientId : number to patient : User and doctorId : number to doctor : User , MOdel/class User 
}
