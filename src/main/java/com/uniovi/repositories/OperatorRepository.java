package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Operator;

public interface OperatorRepository extends CrudRepository<Operator, Long> {

	List<Operator> findByOperatorname(String operatorName);

	Operator findByEmail(String email);

	@Query("SELECT op from Operator op where op.email = ?1 and op.password = ?2")
	Operator isInDb(String email, String password);
	
}
