package com.uniovi.kafka;

import javax.annotation.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniovi.entities.Incident;
import com.uniovi.services.AgentsService;
import com.uniovi.services.IncidentsService;
import com.uniovi.services.OperatorsService;

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
	private OperatorsService operatorsService;

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@KafkaListener(topics = "incidents")
	public void onIncident(String data) throws Exception {
		logger.info("General incident received: " + data);
		messagingTemplate.convertAndSend("/incident/standard", data);
		if(data != null && data.trim().length() != 0) {
			ObjectMapper obj = new ObjectMapper();
			Incident incident = obj.readValue(data.getBytes(), Incident.class);
			agentsService.addAgent(incident.getAgent());
			inciService.addIncident(incident);	
			operatorsService.increaseNotificationCount(incident);
		}
	}
}
