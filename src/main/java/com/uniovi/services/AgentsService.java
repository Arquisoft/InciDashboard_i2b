package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.AgentInfo;
import com.uniovi.repositories.AgentsRepository;

@Service
public class AgentsService {
	
	@Autowired
	private AgentsRepository agentsRepository;

	public void addAgent(AgentInfo agent) {
		this.agentsRepository.save(agent);
	}
	
	public void deleteAgent(AgentInfo agent) {
		this.agentsRepository.delete(agent);
	}

	public void deleteAll() {
		this.agentsRepository.deleteAll();
	}

}
