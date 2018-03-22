package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Operator;
import com.uniovi.services.OperatorService;


@Controller
public class OperatorController {
		
	@Autowired
	private OperatorService service;
	
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String getLogin(Model model) {
		return "/login";
	}
	
	//Lo pongo con otra url porque no tira sino
	@RequestMapping(value = "/login/post", method = RequestMethod.POST)
	public String adminLogin(@RequestParam String email, @RequestParam String password) {
		Operator op = service.isUser(email,password);
		if(op !=null) {
			return "dashboard";
		} else {
			return "redirect:/login?error";
		}
	}

}
