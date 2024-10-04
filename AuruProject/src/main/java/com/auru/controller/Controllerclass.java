package com.auru.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.auru.model.EventDetails;
import com.auru.serviceimpl.EventImpl;






@RestController
public class Controllerclass {
	
	
	@Autowired
	EventImpl eventImpl;
	
	@GetMapping("/")
	public String welcomePage()
	{
		return "Welcome to Auru";
	}
	@PostMapping("/addEvent")
	public String addEvent(@Valid @RequestBody EventDetails evd) 
	{
		System.out.println("Inside Controller");	

		String result = eventImpl.addEvent(evd);
		
		return result;
	}

	
	@GetMapping("/searchEvent/{eventName}")
	public EventDetails searchEvent(@PathVariable String eventName)
	{
		EventDetails evd = new EventDetails();
		evd = eventImpl.searchByName(eventName);
		return evd; 
	}
	
	@GetMapping("/searchEvent/values=")
	public List<EventDetails> searchEvent(@RequestParam String[] values)
	{
		List<EventDetails> evd = new ArrayList<EventDetails>();
		System.out.println(values[0] +"," + values[1]);
		evd = eventImpl.searchEventbyDate(values[0],values[1]);
		return evd; 
	}
	
	@DeleteMapping("/deleteEvent/{eventName}")
	public String deleteEvent(@PathVariable String eventName)
	{
		String result = eventImpl.deleteByName(eventName);
		return result; 
	} 
	
	@PutMapping("/updateEvent")
	public String updateEvent(@RequestBody EventDetails evn)
	{
		String result = eventImpl.updateEvent(evn);
		return result; 
	} 
	
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	 public Map<String,String> HandleValidationException(MethodArgumentNotValidException ex)
	 {
		 Map<String,String> errors = new HashMap<String,String>();
		 
		 ex.getBindingResult().getAllErrors().stream().forEach(
				 (error) -> {
					 String fieldName =((FieldError) error).getField();
					 String errorMessage = error.getDefaultMessage();
					 errors.put(fieldName, errorMessage);
				 }
				 
				 );
		 return errors;
	 }
		

}
