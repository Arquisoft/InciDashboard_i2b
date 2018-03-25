package com.uniovi.controllers;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniovi.entities.Incident;
import com.uniovi.entities.Operator;
import com.uniovi.services.IncidentsService;
import com.uniovi.services.OperatorsService;


@Controller
public class OperatorController {
	
	@Autowired
	private IncidentsService incidentsService;
	
	@Autowired
	private OperatorsService operatorsService;
	
	@RequestMapping("/login")
	public String getLogin() {
		return "/login";
	}
	
	@RequestMapping("/incidents")
	public String getOperatorIncidents(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		Operator operator = operatorsService.getOperatorByEmail(email);
		
		List<Incident> incidents = incidentsService.getIncidentsOf(operator);
		model.addAttribute("incidents", incidents);
		model.addAttribute("opEmail", email);
		
		return "incidentsView";
	}
	
	@RequestMapping("/incident/{inciName}/details")
	public String getIncidentDetails(Model model, @PathVariable String inciName) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		Operator operator = operatorsService.getOperatorByEmail(email);
		Incident incident = incidentsService.getIncidentByName(inciName);
		
		model.addAttribute("incident", incident);
		return "incidentDetails";
	}

}
