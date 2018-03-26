package com.uniovi.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.uniovi.entities.Incident;

public interface IncidentsRepository extends MongoRepository<Incident, ObjectId> {

	public List<Incident> findAll();
	
	public Incident findByInciName (String inciName);

	public void deleteByInciName(String inciName);

	@Query("{ 'location.lat': { $exists: true }, 'location.lon': {$exists: true} }")
	public List<Incident> findGeolocated();

	@Query("{ 'properties.temperature': { $exists: true } }")
	public List<Incident> findWithTemperature();
	
	@Query("{ 'tags': { $exists : true } }'")
	public List<Incident> findWithTags();

	@Query("{ 'agent.kind': ?0 }")
	public List<Incident> findByKind(String kind);

	@Query("{ 'properties.operator': ?0 }'")
	public List<Incident> findByOperatorEmail(String email);

}
