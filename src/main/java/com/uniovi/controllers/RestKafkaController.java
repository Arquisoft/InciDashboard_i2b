package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uniovi.services.KafkaService;

@RestController
public class RestKafkaController {
	
	@Autowired
	private KafkaService service;
	
	@RequestMapping("/kafka")
	public String getKafkaGeo() {
		return "";
	}

}
