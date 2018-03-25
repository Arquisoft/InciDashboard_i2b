package com.uniovi.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.uniovi.entities.AgentInfo;

public interface AgentsRepository extends MongoRepository<AgentInfo, ObjectId> {

}
