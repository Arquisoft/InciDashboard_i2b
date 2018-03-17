package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.AgentInfo;
import com.uniovi.entities.Incident;
import com.uniovi.util.RandomIncidentGenerator;

@Service
public class InsertTestDataService {
	
	@Autowired
	private AgentsService agentsService;

	@Autowired
	private RandomIncidentGenerator incidentGenerator;
	
	@Autowired
	private IncidentsService incidentsService;
	
	private List<AgentInfo> agents;
	
	private final static int NUM_INCIDENTS = 1000;
	
	@PostConstruct
	public void init() {
		agents = new ArrayList<AgentInfo>();
		agents.add(new AgentInfo("agent1", "pruebas123", "Person"));
		agents.add(new AgentInfo("agent2", "pruebas456", "Entity"));
		agents.add(new AgentInfo("Paco", "123456", "Person"));
		agents.add(new AgentInfo("agent3", "pruebas789", "Sensor"));
		
		for (AgentInfo agent : agents) {
			agentsService.addAgent(agent);
		}
		
		incidentGenerator.setPossibleAgents(this.agents);
		
		for (int i = 0; i < NUM_INCIDENTS; i++) {
			Incident incident = incidentGenerator.generateRandomIncident();
			incidentsService.addIncident(incident);
		}
	}
	
}
