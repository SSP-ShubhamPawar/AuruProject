package com.auru.service;

import java.util.List;

import com.auru.model.EventDetails;


public interface Event  {

	public String addEvent(EventDetails evndtls);
	public List<EventDetails> searchEventbyDate(String fromDate,String toDate);
	public EventDetails searchByName(String eventName);
}
