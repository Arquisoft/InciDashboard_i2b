package com.uniovi.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uniovi.entities.Incident;
import com.uniovi.entities.Operator;
import com.uniovi.services.SecurityService;
import com.uniovi.util.Checker;

@Controller
public class OperatorController extends AppController{

	@Autowired
	private SecurityService securityService;
	
	@RequestMapping("/login")
	public String getLogin() {
		return "login";
	}

	@RequestMapping("/incidents")
	public String getOperatorIncidents(Model model, Principal principal) {
		Operator operator = operatorsService.getOperatorByEmail(principal.getName());
		operatorsService.resetNotificationCount(operator);
		List<Incident> incidents = incidentsService.getIncidentsOf(operator);
		model.addAttribute("incidents", incidents);
		model.addAttribute("opEmail", principal.getName());
		// notifications are erased when looking at incidents
		model.addAttribute("numNotifications", 0);
		model.addAttribute("numIncidents", this.getIncidencesOfCurrentOp());
		model.addAttribute("role",operator.getRole());
		operatorPermissions(model,operator);
		return "incidentsView";
	}

	@RequestMapping("/incident/{inciName}/details")
	public String getIncidentDetails(Model model, @PathVariable String inciName, Principal principal) {

		List<Incident> incidents = incidentsService
				.getIncidentsOf(operatorsService.getOperatorByEmail(principal.getName()));
		Incident a = incidents.stream()
				.filter(inci -> inci.getInciName().equals(inciName))
				.findFirst().orElse(null);
		if (a == null) {
			return "redirect:/dashboard/maps?error";
		}
		Operator operator = operatorsService.getOperatorByEmail(principal.getName());
		List<String> states = incidentsService.getAvailableStates();
		Incident incident = incidentsService.getIncidentByName(inciName);
		model.addAttribute("states", states);
		model.addAttribute("incident", incident);
		model.addAttribute("numNotifications", 0);
		model.addAttribute("role", operator.getRole());
		operatorPermissions(model,operator);
		return "incidentDetails";
	}

	@RequestMapping(value = "/incident/addComment", method = RequestMethod.POST)
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

	@RequestMapping(value = "/incident/changeState", method = RequestMethod.POST)
	@ResponseBody
	public String changeState(@RequestParam("name") String name, @RequestParam("state") String state) {
		incidentsService.changeState(name, state);
		return "State changed";
	}

	// Admin
	@RequestMapping("/admin/login")
	public String getAdminLogin() {
		return "/admin-login";
	}

	@RequestMapping(value = "/admin/login", method = RequestMethod.POST)
	public String adminLogin(Model model, @RequestParam String email, @RequestParam String password) {
		Operator operator = operatorsService.getOperatorByEmail(email);

		if (operator != null && operatorsService.isValidOperator(operator) && operator.getRole().equals("ROLE_ADMIN")) {
			securityService.autoLogin(email, password);
			return "redirect:/admin/operators";
		} else {
			model.addAttribute("error", true);
			return "redirect:/admin/login?error";
		}
	}

	@RequestMapping(value = "/admin/operators", method = RequestMethod.GET)
	public String getOperators(Model model, Principal principal) {
		model.addAttribute("operators", operatorsService.getAllOperatorsBut(principal.getName()));
		addCommonAttributes(model, principal);
		return "operators";
	}

	@RequestMapping(value = "/operator/admin", method = RequestMethod.POST)
	@ResponseBody
	public String changePermissions(@RequestParam String id) {
		Checker.isEmpty(id);
		Checker.isNull(id);
		operatorsService.changePermissions(id);
		return "Permissions changed";
	}
	

	
}
