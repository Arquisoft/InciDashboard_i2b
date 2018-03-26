package com.uniovi.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		
		try {
			IncidentState state = IncidentState.valueOf(stateStr);
			incident.setState(state);
			incidentsRepository.save(incident);
		} catch (Exception e) {
			return;
		}
	}

	public Map<String, Integer> getMostUsedTags() {
		List<Incident> incidents  = incidentsRepository.findWithTags();
		Map<String,Integer> usedTags = new HashMap<>();
		Integer tagcount = null;
		for (Incident incident : incidents) {
			for (String t : incident.getTags()) {
				if((tagcount = usedTags.get(t))!=null) {
					usedTags.put(t, ++tagcount);
				}else {
					usedTags.put(t, 1);
				}
			}
		}
		return usedTags;
	}

}
