package com.auru.model;

public class EventDetails 
{

	public int eventId;
	public String eventName;
	public String eventDate;
	public String eventType;
	public String fileLocation;
	public String weblink;
	
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
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
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
	
	@Override
	public String toString() {
		return "EventDetails [eventId=" + eventId + ", eventName=" + eventName + ", eventDate=" + eventDate
				+ ", eventType=" + eventType + ", fileLocation=" + fileLocation + ", weblink=" + weblink + "]";
	}

	
	
}
