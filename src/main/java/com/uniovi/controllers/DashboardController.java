package com.uniovi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uniovi.entities.Incident;
import com.uniovi.entities.location.LatLng;
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
		//TEST
		incidents = new ArrayList<>();
		incidents.add(new Incident("GEO1", new LatLng(43.3395170,-5.4949041)));
		//
		model.addAttribute("incidents", incidents);
		return "maps";
	}

	@RequestMapping(value = "/dashboard/charts", method = RequestMethod.GET)
	public String getDashboardCharts(Model model) {
		List<Incident> temperatureIncidents = inciService.getTemperatureSensorIncidents();		
//TEST
//		incidents = new ArrayList<>();
//		incidents.add(new Incident("GEO1", new LatLng(43.3395170,-5.4949041)));
//		//
//		model.addAttribute("incidents", incidents);
		return "charts";
	}

	@RequestMapping(value = "/dashboard/realTime", method = RequestMethod.GET)
	public String getDashboardChart(Model model) {
		List<Incident> incidentsSensors = inciService.getKindIncidents("Sensor");
		List<Incident> incidentsPeople = inciService.getKindIncidents("Person");
		List<Incident> incidentsEntities = inciService.getKindIncidents("Entity");
		model.addAttribute("sensors", incidentsSensors);
		model.addAttribute("people", incidentsPeople);
		model.addAttribute("entities", incidentsEntities);
		return "incidentsView";
	}

	@SubscribeMapping("/test-data")
	public String getTestData(Model model) throws JsonProcessingException {
		return testDataService.getTestDataAsJSON();
	}

}
