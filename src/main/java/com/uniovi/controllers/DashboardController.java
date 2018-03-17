package com.uniovi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Incident;
import com.uniovi.services.IncidentsService;

@Controller
public class DashboardController {
	
	@Autowired
	private IncidentsService incidentsService;

	@RequestMapping(value="/dashboard", method=RequestMethod.GET)
	public String getDashboard() {
		return "dashboard";
	}
	
	 @SubscribeMapping("/test-data")
	 public String getTestData() {
		 List<Incident> incidents = incidentsService.getAllIncidents();
		 
		 return incidents.toString();
	 }
	
}
