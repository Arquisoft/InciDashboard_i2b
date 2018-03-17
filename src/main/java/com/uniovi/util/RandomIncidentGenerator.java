package com.uniovi.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.uniovi.entities.AgentInfo;
import com.uniovi.entities.Incident;
import com.uniovi.entities.location.LatLng;

/**
 * This class creates an incident with random values. It is
 * used in the InsertTestDataService class to add some random
 * incidents so we can check that the application works as expected.
 *
 */
@Component
public class RandomIncidentGenerator {
	
	private List<AgentInfo> possibleAgents;
	
	private Random generator;
	
	private final static String ALPHANUM = 
			"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	
	private int nameLength;

	public RandomIncidentGenerator() {
		this.possibleAgents = new ArrayList<AgentInfo>();
		this.generator = new Random();
		this.nameLength = 10;
	}
	
	public void setPossibleAgents(List<AgentInfo> possibleAgents) {
		this.possibleAgents = possibleAgents;
	}
	
	public Incident generateRandomIncident() {
		Incident incident = new Incident();
		incident.setInciName(this.createRandomString(this.nameLength));
		incident.setAgent(this.pickRandomAgent());
		incident.setLocation(this.createRandomLocation());
		this.createRandomPropertiesFor(incident);
		return incident;
	}
	
	private void createRandomPropertiesFor(Incident incident) {
		Map<String, Object> properties = new HashMap<String, Object>();
		
		properties.put("priority", generator.nextInt(4));
		
		if (incident.getAgent().getKind().equals("Sensor")) {
			double temperature = generator.nextDouble() * 80 - 30; 
			properties.put("temperature", temperature);
		}
		
		incident.setProperties(properties);
	}

	private LatLng createRandomLocation() {
		// latitude between -90 and 90
		double lat = (generator.nextDouble() - 0.5) * 180;

		// longitude between -180 and 180
		double lon = (generator.nextDouble() - 0.5) * 360;
		
		return new LatLng(lat, lon);
	}

	private AgentInfo pickRandomAgent() {
		if (this.possibleAgents.size() == 0) {
			return null;
		}
		
		int agentIndex = this.generator.nextInt(this.possibleAgents.size());
		return this.possibleAgents.get(agentIndex);
	}

	private String createRandomString(int length) {
		String randomStr = "";
		for (int i = 0; i < length; i++) {
			int charIndex = generator.nextInt(ALPHANUM.length());
			randomStr += ALPHANUM.charAt(charIndex);
		}
		return randomStr;
	}
	
}
