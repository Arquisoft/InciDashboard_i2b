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
import com.uniovi.json.IncidentDeserializer;
import com.uniovi.services.IncidentsService;

/**
 * This class is responsible of obtaining the incidents
 * sent through the IncidentManager, and doing the suitable
 * operation depending on the topic of the incident received.
 */
@ManagedBean
public class IncidentListener {
	
    private static final Logger logger = LoggerFactory.getLogger(IncidentListener.class);
    
    @Autowired
    private IncidentsService inciService;
    
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topics="standard")
    public void onStandardIncident(String data) throws JsonParseException, JsonMappingException, IOException {
    		logger.info("Standard incident received: " + data);
    		messagingTemplate.convertAndSend("/incident/standard", data);
    		ObjectMapper obj = new ObjectMapper();
    		Incident incident = obj.readValue(data.getBytes(), Incident.class);
    }
    
    @KafkaListener(topics="geolocated")
    public void onGeolocatedIncident(String data) throws JsonParseException, JsonMappingException, IOException {
    		logger.info("Geolocated incident received: " + data);
    		messagingTemplate.convertAndSend("/incident/geolocated", data);
    		ObjectMapper obj = new ObjectMapper();
    		Incident incident = obj.readValue(data.getBytes(), Incident.class);
    }
    
    @KafkaListener(topics="withOperator")
    public void onOperatorIncident(String data) throws JsonParseException, JsonMappingException, IOException {
		logger.info("Incident with an assigned operator received: " + data);
		messagingTemplate.convertAndSend("/incident/withOperator", data);
		ObjectMapper obj = new ObjectMapper();
		Incident incident = obj.readValue(data.getBytes(), Incident.class);
    }
    
    @KafkaListener(topics="sensor")
    public void onSensorIncident(String data) throws JsonParseException, JsonMappingException, IOException {
		logger.info("Incident coming from a sensor received: " + data);
		messagingTemplate.convertAndSend("/incident/sensor", data);
		ObjectMapper obj = new ObjectMapper();
		Incident incident = obj.readValue(data.getBytes(), Incident.class);
    }

}
