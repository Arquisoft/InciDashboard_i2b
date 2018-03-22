package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniovi.entities.AgentInfo;
import com.uniovi.entities.Incident;
import com.uniovi.entities.Operator;
import com.uniovi.util.RandomIncidentGenerator;

@Service
public class InsertTestDataService {
	
	@Autowired
	private AgentsService agentsService;

	@Autowired
	private RandomIncidentGenerator incidentGenerator;
	
	@Autowired
	private IncidentsService incidentsService;
	@Autowired
	private OperatorService opService;
	
	private List<AgentInfo> agents;
	private List<Incident> incidents;
	
	private final static int NUM_INCIDENTS = 1000;
	
	private String incidentsJson;
	
	@PostConstruct
	public void init() throws JsonProcessingException {
		
		Operator op1 = new Operator( "pedro@gmail.com", "PEDRO", false);
		op1.setPassword("123456");
		opService.addOperator(op1);
		
		Operator op2 = opService.isUser("pedro@gmail.com","123456");
		op2.equals(op1);
		
		agents = new ArrayList<AgentInfo>();
		incidents = new ArrayList<Incident>();
		
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
			incidents.add(incident);
			incidentsService.addIncident(incident);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		this.incidentsJson = mapper.writeValueAsString(incidents);
		
		
	}

	public String getTestDataAsJSON() {
		return this.incidentsJson;
	}
	
}
