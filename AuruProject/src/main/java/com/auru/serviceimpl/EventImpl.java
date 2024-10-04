package com.auru.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

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
		
		String query = "insert into AURUEVENT (EVENTID,EVENTNAME,EVENTDATE,EVENTTYPE,BASE64FILE,WEBLINK,FILELOCATION)"
				+ " values(?,?,?,?,?,?,?);";
		
		int res = jdbctemp.update(query,evndtls.getEventId(),evndtls.getEventName(),evndtls.getEventDate(),
				evndtls.getEventType(),base64data,evndtls.getWeblink(),evndtls.getFileLocation());
		 result = (res>0)? " Data added succesfully " : "Data failed to save "; 
		return result;
	}

	@Override
	public List<EventDetails> searchEventbyDate(String fromDate , String toDate) {
		// TODO Auto-generated method stub
		System.out.println(fromDate+","+toDate);
		//EventDetails enDv = new EventDetails();
		String Query  ="SELECT EVENTID,EVENTNAME,EVENTDATE,EVENTTYPE,BASE64FILE,WEBLINK,FILELOCATION FROM AURUEVENT WHERE EVENTDATE BETWEEN '"+fromDate+"' AND '"+toDate+"'";
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
						enDv.setFileLocation(rs.getNString("FILELOCATION"));
						return enDv;
					}
			
				});
		
		return mquery;
	}



	@Override
	public EventDetails searchByName(String eventName) {
		// TODO Auto-generated method stub
		EventDetails enDv = new EventDetails();
		String Query  ="SELECT EVENTID,EVENTNAME,EVENTDATE,EVENTTYPE,BASE64FILE,WEBLINK,FILELOCATION FROM AURUEVENT WHERE EVENTNAME = ? ";
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
						enDv.setFileLocation(rs.getNString("FILELOCATION"));
						
						return enDv;
					}
			
				},eventName);
		
		return enDv;
	}

	public String deleteByName(String eventName) {
		// TODO Auto-generated method stub
		
		String query ="DELETE FROM AURUEVENT WHERE EVENTNAME = ?  ";
		int result = jdbctemp.update(query,eventName);
		
		String res = (result > 0 ) ? "Data Deleted  "+ result +" Succefully ": "Data is not deleted";
		
		return res;
	}



	public String updateEvent(EventDetails evn) {
		// TODO Auto-generated method stub
		String resul = null;
		String res =null;
		int result =0;
		int id = evn.getEventId();
		String checkDataPresent = "SELECT COUNT(1) as countRow FROM AURUEVENT WHERE EVENTID = ? ";
		List<Map<String, Object>> count = jdbctemp.queryForList(checkDataPresent,id);
		
		if(count == null || Integer.parseInt(count.get(0).get("countRow").toString()) ==  0 )
		{
			return "Date is not present for EventId ";
		}
		
		String eventName = evn.getEventName();
		Date eventDate = evn.getEventDate(); 
		String eventType = evn.getEventType();
		String weblink = evn.getWeblink();
		String fileLocation = evn.getFileLocation();
		String base64data = null;
		//Check the file location is same or different 
		String checkFilelocation = "SELECT FILELOCATION FROM AURUEVENT WHERE EVENTID = ? ";
		List<Map<String, Object>> fileLoc = jdbctemp.queryForList(checkFilelocation,id);
		
		if(fileLoc == null)
		{
			String files = fileLoc.get(0).get("FILELOCATION").toString();
			if(!files.equals(fileLocation)) 
			{
				File file = new File(fileLocation);
				try(FileInputStream fis = new FileInputStream(file))
				{
					int fileSize =(int) file.length();
					byte[] fileData = new byte[fileSize];
					int dataByte = fis.read(fileData);
					base64data = Base64.getEncoder().encodeToString(fileData);
					System.out.println("Data file read ");	

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					resul = e.getMessage();
					return resul;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					resul = e.getMessage();
					return resul;
				}

				
				String query ="UPDATE AURUEVENT SET EVENTNAME=?,EVENTDATE=?,EVENTTYPE=?,BASE64FILE=?,WEBLINK=?,FILELOCATION=? WHERE EVENTID = ? " ;
				 result = jdbctemp.update(query,eventName,eventDate,eventType,base64data,weblink,fileLocation,id);
				
				 res = (result > 0 ) ? "Data updated successfully": "Data is not updated";
				
				return res;
				
			}
			
		}
		
		
 		
		String query ="UPDATE AURUEVENT SET EVENTNAME=?,EVENTDATE=?,EVENTTYPE=?,WEBLINK=?,FILELOCATION=? WHERE EVENTID = ? " ;
		 result = jdbctemp.update(query,eventName,eventDate,eventType,weblink,fileLocation,id);
		
		 res = (result > 0 ) ? "Data updated successfully": "Data is not updated";
		
		return res;
	}
	
	

	
	
	

}
