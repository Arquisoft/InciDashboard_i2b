package com.uniovi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	@ResponseBody
	public String getHome(Model model) {
		
		return "<p>ESTAMOS EN DESARROLLO</p>";
	}
}
