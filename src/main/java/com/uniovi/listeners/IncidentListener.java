package com.uniovi.listeners;

import java.io.IOException;

import javax.annotation.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniovi.entities.Incident;
import com.uniovi.services.AgentsService;
import com.uniovi.services.IncidentsService;

/**
 * This class is responsible of obtaining the incidents sent through the 
 * IncidentManager, and doing the suitable operation depending on the topic of
 * the incident received.
 */
@ManagedBean
public class IncidentListener {

	private static final Logger logger = LoggerFactory.getLogger(IncidentListener.class);

	@Autowired
	private IncidentsService inciService;
	
	@Autowired
	private AgentsService agentsService;

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@KafkaListener(topics = "incidents")
	public void onIncident(String data) {
		logger.info("General incident received: " + data);
		messagingTemplate.convertAndSend("/incident/standard", data);
		try {
			if(data != null && data.trim().length() != 0) {
				ObjectMapper obj = new ObjectMapper();
				Incident incident = obj.readValue(data.getBytes(), Incident.class);
				agentsService.addAgent(incident.getAgent());
				inciService.addIncident(incident);	
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/*
	@KafkaListener(topics = "standard")
	public void onStandardIncident(String data) {
		logger.info("Standard incident received: " + data);
		messagingTemplate.convertAndSend("/incident/standard", data);
	}

	@KafkaListener(topics = "geolocated")
	public void onGeolocatedIncident(String data) {
		logger.info("Geolocated incident received: " + data);
		messagingTemplate.convertAndSend("/incident/geolocated", data);
	}

	@KafkaListener(topics = "withOperator")
	public void onOperatorIncident(String data) {
		logger.info("Incident with an assigned operator received: " + data);
		messagingTemplate.convertAndSend("/incident/withOperator", data);
	}

	@KafkaListener(topics = "sensor")
	public void onSensorIncident(String data) {
		logger.info("Incident coming from a sensor received: " + data);
		messagingTemplate.convertAndSend("/incident/sensor", data);
	}
*/
}
