package com.uniovi.services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Incident;
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
	
	public Incident getIncidentByName(String inciName) {
		return incidentsRepository.findByInciName(inciName);
	}

	public void deleteIncidentByName(String inciName) {
		incidentsRepository.deleteByInciName(inciName);
	}

	public void deleteIncidentById(ObjectId id) {
		incidentsRepository.deleteById(id);
	}

}
