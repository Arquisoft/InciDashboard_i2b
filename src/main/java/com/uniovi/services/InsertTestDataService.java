package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

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
	private OperatorsService operatorsService;

	public final static int NUM_INCIDENTS = 10;

	private String incidentsJson;

	@PostConstruct
	public void init() throws JsonProcessingException {
		Operator op1 = new Operator("operator1@dashboard.com", "op1", "123456", "ROLE_OPERATOR");
		Operator op2 = new Operator("operator2@dashboard.com", "op2", "123456", "ROLE_OPERATOR");
		Operator op3 = new Operator("operator3@dashboard.com", "op3", "123456", "ROLE_OPERATOR");
		//Changed, check that InciCommon is up to date CHANGED BOOLEAN ADMIN BY ROLES
		Operator op4 = new Operator("admin1@dashboard.com", "admin1", "123456", "ROLE_ADMIN");
		Operator op5 = new Operator("admin2@dashboard.com", "admin2", "123456", "ROLE_ADMIN");

		operatorsService.addOperator(op1);
		operatorsService.addOperator(op2);
		operatorsService.addOperator(op3);
		operatorsService.addOperator(op4);
		operatorsService.addOperator(op5);

		List<AgentInfo> agents = new ArrayList<AgentInfo>();
		List<Incident> incidents = new ArrayList<Incident>();

		agents.add(new AgentInfo("agent1", "pruebas123", "Person"));
		agents.add(new AgentInfo("agent2", "pruebas456", "Entity"));
		agents.add(new AgentInfo("Paco", "123456", "Person"));
		agents.add(new AgentInfo("agent3", "pruebas789", "Sensor"));

		for (AgentInfo agent : agents) {
			agentsService.addAgent(agent);
		}

		incidentGenerator.setPossibleAgents(agents);

		for (int i = 0; i < NUM_INCIDENTS; i++) {
			Incident incident = incidentGenerator.generateRandomIncident();
			incidents.add(incident);

			if (i <= 3) {
				incidents.get(i).assignOperator(op1);
			} else if (i <= 6) {
				incidents.get(i).assignOperator(op2);
			} else if (i <= 9) {
				incidents.get(i).assignOperator(op3);
			}

			incidentsService.addIncident(incident);
		}

		ObjectMapper mapper = new ObjectMapper();
		this.incidentsJson = mapper.writeValueAsString(incidents);
	}

	@PreDestroy
	public void finalize() {
		this.deleteAll();
	}

	public void deleteAll() {
		incidentsService.deleteAll();
		agentsService.deleteAll();
		operatorsService.deleteAll();
	}

	public String getTestDataAsJSON() {
		return this.incidentsJson;
	}

}
