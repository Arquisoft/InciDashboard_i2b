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

}
