package com.auru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auru.model.EventDetails;
import com.auru.serviceimpl.EventImpl;






@RestController
public class Controllerclass {
	
	
	@Autowired
	EventImpl eventImpl;
	
	@PostMapping("/addEvent")
	public String addEvent(@RequestBody EventDetails evd) 
	{
		System.out.println("Inside Controller");	

		String result = eventImpl.addEvent(evd);
		
		return result;
	}
		

}
