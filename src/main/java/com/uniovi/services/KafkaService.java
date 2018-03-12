package com.uniovi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * This class is responsible of obtaining the incidents
 * sent through the IncidentManager, and doing the suitable
 * operation depending on the topic of the incident received.
 */
@Service
public class KafkaService {
	
    private static final Logger logger = LoggerFactory.getLogger(KafkaService.class);

    @KafkaListener(topics="standard")
    public void onStandardIncident(String data) {
    		logger.info("Standard incident received: " + data);
    }
    
    @KafkaListener(topics="geolocated")
    public void onGeolocatedIncident(String data) {
    		logger.info("Geolocated incident received: " + data);
    }
    
    @KafkaListener(topics="withOperator")
    public void onOperatorIncident(String data) {
		logger.info("Incident with an assigned operator received: " + data);
    }
    
    @KafkaListener(topics="sensor")
    public void onSensorIncident(String data) {
		logger.info("Incident coming from a sensor received: " + data);
    }

}
