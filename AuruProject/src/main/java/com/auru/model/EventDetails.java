package com.auru.model;

import java.sql.Date;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EventDetails 
{
	@Valid
	
	@NotNull(message =" eventId cannot be null")
	public int eventId;
	
	@NotNull(message =" eventName cannot be null")
	@Size(min=2 ,max =30)
	public String eventName;
	
	@NotNull(message ="eventDate cannot be null")
	//@FutureOrPresent(message ="Date cannot be previous days")
	public Date eventDate;
	
	@NotNull(message ="eventType cannot be null")
	public String eventType;
	
	@NotNull(message ="fileLocation cannot be null")
	public String fileLocation;
	
	@NotNull(message ="weblink cannot be null")
	public String weblink;
	
	public String base64File;
	
	public String getBase64File() {
		return base64File;
	}
	public void setBase64File(String base64File) {
		this.base64File = base64File;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getFileLocation() {
		return fileLocation;
	}
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	public String getWeblink() {
		return weblink;
	}
	public void setWeblink(String weblink) {
		this.weblink = weblink;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	@Override
	public String toString() {
		return "EventDetails [eventId=" + eventId + ", eventName=" + eventName + ", eventDate=" + eventDate
				+ ", eventType=" + eventType + ", fileLocation=" + fileLocation + ", weblink=" + weblink
				+ ", base64File=" + base64File + "]";
	}

	
	
	

	
	
}
