package com.uniovi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	@RequestMapping("/home")
	@ResponseBody
	public String getHome(Model model) {
		
		return "<p>ESTAMOS EN DESARROLLOoooo</p>";
	}
}
