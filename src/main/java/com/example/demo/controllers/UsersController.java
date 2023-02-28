package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.AppUser;
import com.example.demo.models.Appointment;
import com.example.demo.services.UserService;

@RestController 
@RequestMapping("/users")
@CrossOrigin 
//controller take care of all the HTTP stuff (annotations and the path) while service take care of all the logic 
public class UsersController {
	//make this UserService accessible throughout the entire object
	private UserService service;
	
	//Dependency injection
	//to connect between service and controller and be able to call these methods inside the controller
	//create java constructor:
	//Constructor has the same name as the class`s name 
	
	//if we have more than one constructor , we can user @Autowired
	//@Autowired: which is explicitly stating that whenever spring tries to create a controller, use this controller and should inject any dependencies that are listed in the argument list
	public	UsersController(UserService service) {
		//initialize that field to whatever gets passed in to my constructor
		this.service = service;		
	}
	
	
	// FE: this.http.get<User[]>(`http://localhost:8080/users?username=${username}&password=${password}`}
	//	   HttpClient made an http request
	// BE: Tomcat received the request
	//     Tomcat called the function below b/c it mapped to /users, Get
	

	
	
	//if we want to throw exception with Get method we can code it in this way:
	@GetMapping
	public AppUser getByUsernameAndPassword(@RequestParam String username,@RequestParam String password ) {
		//this.http.get<User[]>(`http://localhost:8080/users?username=${username}&password=${password}`}
		final var user = service.getByUsernameAndPassword(username, password);
	
	if(user == null) 
		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
	
	return user;
	}

	//this.http.post(`http://localhost:3000/users`,{id: null, username,password,doctor})
	@PostMapping
	public void  register(@RequestBody AppUser user) {
		try {
			service.register(user);
		}catch(Exception e){
		throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
		}
	}
	
	
	//other 2 ways to rewrite the Get method:
	//@GetMapping
	//public User getByUsernameAndPassword(@RequestParam String username,@RequestParam String password ) {
		//--we have the UserController Object, so we can use it inside our methods
		//return service.getByUsernameAndPassword(username, password);
			//--this method  response to the method inside the service getByUsernameAndPassword() which return user or null
		//};
	
	//@GetMapping
	//public ResponseEntity<User> getByUsernameAndPassword(@RequestParam String username,@RequestParam String password ) {
		//final var user = service.getByUsernameAndPassword(username, password);
	
	//if(user == null) 
		//return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	
	//return new ResponseEntity<>(user, HttpStatus.OK);
	//};
	
	
	//calling the addAvailability method
	@PutMapping("/{id}")
	//assume that the appointment is valid
	public void addAppointment(@PathVariable long id, @RequestBody Appointment appointment) {
		try {
			service.addAvailability(id, appointment);
		}catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);

		}
	}
	
	

	
}
