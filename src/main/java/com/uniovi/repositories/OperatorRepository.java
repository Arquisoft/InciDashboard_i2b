package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Operator;

public interface OperatorRepository extends CrudRepository<Operator, Long> {

	List<Operator> findByOperatorname(String operatorName);
	
	//@Query("select r from operator r where r.canRead = ?1")
	//void searchMe(int permission);
}
