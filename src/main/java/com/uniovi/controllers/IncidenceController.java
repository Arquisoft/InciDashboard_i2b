package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniovi.services.KafkaService;

@Controller
public class IncidenceController {
	
	@Autowired
	private KafkaService service;
	
	@RequestMapping("/charts/maps/8")
	public String getMaps(Model model) {
		
		return "map";
	}
	
	@RequestMapping("/charts/geochart")
	public String getGeo(Model model) {
		
		return "geochart";
	}
}
