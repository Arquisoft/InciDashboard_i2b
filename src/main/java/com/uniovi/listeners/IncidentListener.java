package com.uniovi.listeners;

import javax.annotation.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * This class is responsible of obtaining the incidents
 * sent through the IncidentManager, and doing the suitable
 * operation depending on the topic of the incident received.
 */
@ManagedBean
public class IncidentListener {
	
    private static final Logger logger = LoggerFactory.getLogger(IncidentListener.class);
    
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topics="standard")
    public void onStandardIncident(String data) {
    		logger.info("Standard incident received: " + data);
    		messagingTemplate.convertAndSend("/incident/standard", data);
    }
    
    @KafkaListener(topics="geolocated")
    public void onGeolocatedIncident(String data) {
    		logger.info("Geolocated incident received: " + data);
    		messagingTemplate.convertAndSend("/incident/geolocated", data);
    }
    
    @KafkaListener(topics="withOperator")
    public void onOperatorIncident(String data) {
		logger.info("Incident with an assigned operator received: " + data);
		messagingTemplate.convertAndSend("/incident/withOperator", data);
    }
    
    @KafkaListener(topics="sensor")
    public void onSensorIncident(String data) {
		logger.info("Incident coming from a sensor received: " + data);
		messagingTemplate.convertAndSend("/incident/sensor", data);
    }

}
