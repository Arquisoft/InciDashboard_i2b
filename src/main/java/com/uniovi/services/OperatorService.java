package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Operator;
import com.uniovi.repositories.OperatorRepository;

@Service
public class OperatorService {

	
	@Autowired
	private OperatorRepository repo;
	
	public void getAll() {
		repo.findAll();
	}
	
	public void addOperator(Operator operator) {
		repo.save(operator);
	}
	
	public Operator getOperatorByEmail(String email) {
		return repo.findByEmail(email);
	}

	public Operator isUser(String email, String password) {
		return repo.isInDb(email,password);
	}

	public void deleteOperator(Operator operator) {
		repo.delete(operator);
	}
	
	
}
