package com.uniovi.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uniovi.entities.Incident;
import com.uniovi.services.IncidentsService;
import com.uniovi.services.InsertTestDataService;

@Controller
public class DashboardController {

	@Autowired
	private InsertTestDataService testDataService;
	
	@Autowired
	private IncidentsService inciService;

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String getDashboard() {
		return "redirect:/dashboard/maps";
	}

	@RequestMapping(value = "/dashboard/maps", method = RequestMethod.GET)
	public String getDashboardMaps(Model model) {
		List<Incident> incidents = inciService.getGeolocatedIncidents();
		model.addAttribute("incidents", incidents);
		return "maps";
	}

	@RequestMapping(value = "/dashboard/charts", method = RequestMethod.GET)
	public String getDashboardCharts(Model model) {
		List<Incident> temperatureIncidents = inciService.getTemperatureSensorIncidents();		
		List<Incident> incidentsSensors = inciService.getKindIncidents("Sensor");
		List<Incident> incidentsPeople = inciService.getKindIncidents("Person");
		List<Incident> incidentsEntities = inciService.getKindIncidents("Entity");
		Map<String,Integer> mostUsedTags = inciService.getMostUsedTags();
		Set<String> strings = new HashSet<>();
		for (String string : mostUsedTags.keySet()) {
			strings.add("'"+string+"'");
		}
		model.addAttribute("sensors", incidentsSensors.size());
		model.addAttribute("people", incidentsPeople.size());
		model.addAttribute("entities", incidentsEntities.size());
		model.addAttribute("temperatures", temperatureIncidents);
		model.addAttribute("keys", strings);
		model.addAttribute("values", mostUsedTags.values());
		model.addAttribute("tags", mostUsedTags);
		return "charts";
	}

	@RequestMapping(value = "/dashboard/realTime", method = RequestMethod.GET)
	public String getDashboardChart(Model model) {
		
		return "incidentsView";
	}

	@SubscribeMapping("/test-data")
	public String getTestData(Model model) throws JsonProcessingException {
		return testDataService.getTestDataAsJSON();
	}

}
