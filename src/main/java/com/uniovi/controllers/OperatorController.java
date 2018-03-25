package com.uniovi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
		operatorsService.resetNotificationCount(operator);
		
		List<Incident> incidents = incidentsService.getIncidentsOf(operator);
		model.addAttribute("incidents", incidents);
		model.addAttribute("opEmail", email);
		// notifications are erased when looking at incidents
		model.addAttribute("numNotifications", 0);
		
		return "incidentsView";
	}
	
	@RequestMapping("/incident/{inciName}/details")
	public String getIncidentDetails(Model model, @PathVariable String inciName) {
		List<String> states =  incidentsService.getAvailableStates();
		Incident incident = incidentsService.getIncidentByName(inciName);
		
		model.addAttribute("states", states);
		model.addAttribute("incident", incident);
		return "incidentDetails";
	}
	
	@RequestMapping(value="/incident/addComment", method=RequestMethod.POST)
	@ResponseBody
	public String addComment(@RequestParam("name") String name, @RequestParam("comment") String comment) {
		Incident incident = incidentsService.getIncidentByName(name);
		if (incident == null) {
			return "Error adding comment!";
		}
		
		incident.addComment(comment);
		incidentsService.addIncident(incident);
		return "Comment added";
	}
	
	@RequestMapping(value="/incident/changeState", method=RequestMethod.POST)
	@ResponseBody
	public String changeState(@RequestParam("name") String name, @RequestParam("state") String state) {
		incidentsService.changeState(name, state);
		return "State changed";
	}

}
