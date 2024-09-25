package com.auru.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.auru.model.EventDetails;
import com.auru.service.Event;

@Service
public class EventImpl implements Event
{
	
	@Autowired
	JdbcTemplate jdbctemp;
	
	@Override
	public String addEvent(EventDetails evndtls) {
		// TODO Auto-generated method stub
		
		System.out.println("Event Called");	

		String base64data =null;
		String result = null;
		String fileloc = evndtls.getFileLocation();
		File file = new File(fileloc);
		try(FileInputStream fis = new FileInputStream(file))
		{
			int fileSize =(int) file.length();
			byte[] fileData = new byte[fileSize];
			int dataByte = fis.read(fileData);
			base64data = Base64.getEncoder().encodeToString(fileData);
			System.out.println("Data file read ");	

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			result = e.getMessage();
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			result = e.getMessage();
			return result;
		}
		
		String query = "insert into AURUEVENT (EVENTID,EVENTNAME,EVENTDATE,EVENTTYPE,BASE64FILE,WEBLINK)"
				+ " values(?,?,?,?,?,?);";
		
		int res = jdbctemp.update(query,evndtls.getEventId(),evndtls.getEventName(),evndtls.getEventDate(),
				evndtls.getEventType(),base64data,evndtls.getWeblink());
		 result = (res>0)? " Data added succesfully " : "Data failed to save "; 
		return result;
	}

	@Override
	public String searchEventbyDate(String Date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String searchAllEvent() {
		// TODO Auto-generated method stub
		return null;
	}

}
