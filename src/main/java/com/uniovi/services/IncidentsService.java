package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Incident;
import com.uniovi.entities.IncidentState;
import com.uniovi.entities.Operator;
import com.uniovi.repositories.IncidentsRepository;

@Service
public class IncidentsService {
	
	@Autowired
	private IncidentsRepository incidentsRepository;

	public List<Incident> getAllIncidents() {
		return incidentsRepository.findAll();
	}
	
	public List<Incident> getGeolocatedIncidents() {
		return incidentsRepository.findGeolocated();
	}
	
	public List<Incident> getTemperatureSensorIncidents() {
		return incidentsRepository.findWithTemperature();
	}
	
	public List<Incident> getKindIncidents(String kind) {
		return incidentsRepository.findByKind(kind);
	}

	public void addIncident(Incident incident) {
		incidentsRepository.save(incident);
	}
	
	public List<Incident> getIncidentsOf(Operator operator) {
		return incidentsRepository.findByOperatorEmail(operator.getEmail());
	}
	
	public Incident getIncidentByName(String inciName) {
		return incidentsRepository.findByInciName(inciName);
	}

	public void deleteIncidentByName(String inciName) {
		incidentsRepository.deleteByInciName(inciName);
	}

	public void deleteIncidentById(ObjectId id) {
		incidentsRepository.deleteById(id);
	}

	public void deleteAll() {
		incidentsRepository.deleteAll();
	}

	public List<String> getAvailableStates() {
		IncidentState[] states = IncidentState.values();
		List<String> statesString = new ArrayList<String>();
		for (int i = 0; i < states.length; i++) {
			statesString.add(states[i].toString());
		}
		return statesString;
	}

	public void changeState(String name, String stateStr) {
		Incident incident = incidentsRepository.findByInciName(name);
		if (incident == null) {
			return;
		}
		
		IncidentState state = IncidentState.valueOf(stateStr);
		if (state != null) {
			incident.setState(state);
			incidentsRepository.save(incident);
		}
	}

}
