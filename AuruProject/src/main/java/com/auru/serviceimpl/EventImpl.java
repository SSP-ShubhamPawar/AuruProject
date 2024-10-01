package com.auru.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
	public List<EventDetails> searchEventbyDate(String fromDate , String toDate) {
		// TODO Auto-generated method stub
		System.out.println(fromDate+","+toDate);
		//EventDetails enDv = new EventDetails();
		String Query  ="SELECT EVENTID,EVENTNAME,EVENTDATE,EVENTTYPE,BASE64FILE,WEBLINK FROM AURUEVENT WHERE EVENTDATE BETWEEN '"+fromDate+"' AND '"+toDate+"'";
		System.out.println("Query = "+Query);

		List<EventDetails> mquery = jdbctemp.query(Query,new RowMapper<EventDetails>()
				{

					@Override
					public EventDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
						// TODO Auto-generated method stub
						System.out.println("rs = "+rs.getInt("EVENTID"));
						EventDetails enDv = new EventDetails();

						enDv.setEventId(rs.getInt("EVENTID"));
						enDv.setEventName(rs.getNString("EVENTNAME"));
						enDv.setEventDate(rs.getDate("EVENTDATE"));
						enDv.setEventType(rs.getNString("EVENTTYPE"));
						enDv.setBase64File(rs.getNString("BASE64FILE"));
						enDv.setWeblink(rs.getNString("WEBLINK"));
						return enDv;
					}
			
				});
		
		return mquery;
	}



	@Override
	public EventDetails searchByName(String eventName) {
		// TODO Auto-generated method stub
		EventDetails enDv = new EventDetails();
		String Query  ="SELECT EVENTID,EVENTNAME,EVENTDATE,EVENTTYPE,BASE64FILE,WEBLINK FROM AURUEVENT WHERE EVENTNAME = ? ";
		List<EventDetails> mquery = jdbctemp.query(Query,new RowMapper<EventDetails>()
				{

					@Override
					public EventDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
						// TODO Auto-generated method stub
						enDv.setEventId(rs.getInt("EVENTID"));
						enDv.setEventName(rs.getNString("EVENTNAME"));
						enDv.setEventDate(rs.getDate("EVENTDATE"));
						enDv.setEventType(rs.getNString("EVENTTYPE"));
						enDv.setBase64File(rs.getNString("BASE64FILE"));
						enDv.setWeblink(rs.getNString("WEBLINK"));
						return enDv;
					}
			
				},eventName);
		
		return enDv;
	}

}
