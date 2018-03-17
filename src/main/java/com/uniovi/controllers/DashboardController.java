package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uniovi.services.InsertTestDataService;

@Controller
public class DashboardController {
	
	@Autowired
	private InsertTestDataService testDataService;
	
	@RequestMapping(value="/dashboard", method=RequestMethod.GET)
	public String getDashboard() {
		return "dashboard";
	}
	
	 @SubscribeMapping("/test-data")
	 public String getTestData() throws JsonProcessingException {
		 return testDataService.getTestDataAsJSON();
	 }
	
}
