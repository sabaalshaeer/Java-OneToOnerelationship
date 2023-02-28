package com.example.demo.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity //When a class is annotated with @Entity, it is typically mapped to a database table,
//The @Entity annotation is a JPA (Java Persistence API) annotation used to mark a Java class as an entity,
//which means that instances of this class can be persisted to a relational database using JPA.
public class AppUser {
	@Id //specific the id as primary key
	@GeneratedValue(strategy = GenerationType.SEQUENCE)//the @GeneratedValue annotation indicates that the value for id should be generated automatically by the database. 
	
	//in Java , 1st we declare the field
	public Long id; // Object Long not Primitive long
	public String username;
	public String password;
	public boolean doctor; // Primitive boolean not Object Boolean
	
	@OneToMany
	public List<Appointment> appointments;
	
	//Declare constructor
	//Constructor in Java have the same name in the class up and there is no return type it assumed that the return type is the User type
	
	//default constructor
	public AppUser() {}
	//in TypeScript: we declare the constructor argument , field and initialization all in one
	
	//2nd we declare constructor`s argument 
	public AppUser(Long id, String username, String password, boolean doctor) {
		//set the field  using 'this.' to local value
		this.id = id;// 1st id is field , 2nd id is local
		this.username = username;
		this.password = password;
		this.doctor = doctor;
		
		}
	}

