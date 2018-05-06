package com.uniovi.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uniovi.entities.Incident;
import com.uniovi.services.InsertTestDataService;

@Controller
public class DashboardController extends AppController {

	@Autowired
	private InsertTestDataService testDataService;

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String getDashboard() {
		return "redirect:/dashboard/maps";
	}

	@RequestMapping(value = "/dashboard/maps", method = RequestMethod.GET)
	public String getDashboardMaps(Model model, Principal principal) {
		addCommonAttributes(model,principal);
		addMapAttributes(model);
		return "maps";
	}

	@RequestMapping(value = "/dashboard/charts", method = RequestMethod.GET)
	public String getDashboardCharts(Model model, Principal principal) {
		addCommonAttributes(model,principal);
		addChartsAttributes(model);
		return "charts";
	}

	@RequestMapping(value = "/dashboard/realTime", method = RequestMethod.GET)
	public String getDashboardChart(Model model, Principal principal) {
		addCommonAttributes(model, principal);
		addRealTimeAttributes(model);
		return "incidentsView";
	}

	@SubscribeMapping("/test-data")
	public String getTestData(Model model) throws JsonProcessingException {
		return testDataService.getTestDataAsJSON();
	}
	
	private void addChartsAttributes(Model model) {
		Map<String, Integer> usedTags = incidentsService.getMostUsedTags();
		List<Incident> incidentsSensors = incidentsService.getKindIncidents("Sensor");
		List<Incident> incidentsPeople = incidentsService.getKindIncidents("Person");
		List<Incident> incidentsEntities = incidentsService.getKindIncidents("Entity");
		model.addAttribute("sensors", incidentsSensors.size());
		model.addAttribute("people", incidentsPeople.size());
		model.addAttribute("entities", incidentsEntities.size());
		model.addAttribute("times",incidentsService.getTemperatureSensorIncidentsDates());
		model.addAttribute("temperatures", incidentsService.getTemperaturesOfSensors());
		model.addAttribute("keys", usedTags.keySet());
		model.addAttribute("values", usedTags.values());
	}

	private void addRealTimeAttributes(Model model) {
		List<Incident> incidentsSensors = incidentsService.getKindIncidents("Sensor");
		List<Incident> incidentsPeople = incidentsService.getKindIncidents("Person");
		List<Incident> incidentsEntities = incidentsService.getKindIncidents("Entity");
		model.addAttribute("sensors", incidentsSensors);
		model.addAttribute("people", incidentsPeople);
		model.addAttribute("entities", incidentsEntities);
	}

	private void addMapAttributes(Model model) {
		List<Incident> incidents = incidentsService.getGeolocatedIncidents();
		model.addAttribute("incidents", incidents);
	}

}
