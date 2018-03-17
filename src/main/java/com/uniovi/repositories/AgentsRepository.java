package com.uniovi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.AgentInfo;

public interface AgentsRepository extends CrudRepository<AgentInfo, Long> {

}
