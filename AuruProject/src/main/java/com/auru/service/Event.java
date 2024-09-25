package com.auru.service;

import com.auru.model.EventDetails;


public interface Event  {

	public String addEvent(EventDetails evndtls);
	public String searchEventbyDate(String Date);
	public String searchAllEvent();
}
