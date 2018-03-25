package com.uniovi.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.uniovi.entities.Operator;

public interface OperatorRepository extends MongoRepository<Operator, ObjectId> {

	List<Operator> findByOperatorname(String operatorName);

	@Query("{ 'email': ?0, 'password': ?1 }")
	Operator isInDb(String email, String password);

	Operator findByEmail(String email);
	
}
