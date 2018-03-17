package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Incident;

public interface IncidentsRepository extends CrudRepository<Incident, Long> {

	public List<Incident> findAll();

	public void deleteByInciName(String inciName);

}
