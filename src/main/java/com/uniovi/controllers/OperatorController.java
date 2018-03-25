package com.uniovi.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Operator;
import com.uniovi.services.OperatorService;


@Controller
public class OperatorController {
		
	@Autowired
	private OperatorService service;
	
	@Autowired
	private HttpSession httpSession;

	@RequestMapping("/login")
	public String getLogin() {
		return "/login";
	}
	
	//Lo pongo con otra url porque no tira sino
	@RequestMapping(value = "/login/post", method = RequestMethod.POST)
	public String login(@RequestParam String email, @RequestParam String password) {
		Operator op = service.isUser(email,password);
		
		if(op !=null) {
			httpSession.setAttribute("email", email);
			return "dashboard";
		} else {
			return "redirect:/login?error";
		}
	}

}
