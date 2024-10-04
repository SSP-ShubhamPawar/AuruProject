package com.auru;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;





@SpringBootApplication
public class AuruProjectMain implements CommandLineRunner {
	
	public static void main(String args[])
	{
		SpringApplication.run(AuruProjectMain.class, args);
		System.out.println("Main method called");	
		
		
	}

	@Override
	public void run(String... args) throws Exception {
		
		// TODO Auto-generated method stub
		
	}

}
