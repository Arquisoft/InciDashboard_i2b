package com.uniovi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChartsController {
	
	
	@RequestMapping("/charts/maps")
	public String getMaps(Model model) {
		
		return "map";
	}
	
	@RequestMapping("/charts/geochart")
	public String getGeo(Model model) {
		
		return "geochart";
	}
}
