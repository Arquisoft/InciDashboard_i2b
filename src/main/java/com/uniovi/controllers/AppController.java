package com.uniovi.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import com.uniovi.entities.Operator;
import com.uniovi.services.IncidentsService;
import com.uniovi.services.OperatorsService;

public class AppController {

	@Autowired
	protected OperatorsService operatorsService;

	@Autowired
	protected IncidentsService incidentsService;

	
	public AppController() {
		super();
	}

	protected void addCommonAttributes(Model model, Principal principal) {
		Operator o = operatorsService.getOperatorByEmail(principal.getName());
		model.addAttribute("opEmail", SecurityContextHolder.getContext().getAuthentication().getName());
		model.addAttribute("numNotifications", this.getNotificationsOfCurrentOp());
		model.addAttribute("numIncidents", this.getIncidencesOfCurrentOp());
		model.addAttribute("role", o.getRole());
		model.addAttribute("incidentModify", o.incidentModify());
		model.addAttribute("chartAccess", o.chartAccess());
		model.addAttribute("mapAccess", o.mapAccess());
	}
	
	protected void operatorPermissions(Model model , Operator o) {
		model.addAttribute("incidentModify", o.incidentModify());
		model.addAttribute("chartAccess", o.chartAccess());
		model.addAttribute("mapAccess", o.mapAccess());
	}
	
	protected int getNotificationsOfCurrentOp() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		Operator operator = operatorsService.getOperatorByEmail(email);
		return operator.getNumNotifications();
	}
	
	protected int getIncidencesOfCurrentOp() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		Operator operator = operatorsService.getOperatorByEmail(email);
		return incidentsService.getIncidentsOf(operator).size();
	}

	
	
}