package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Operator;
import com.uniovi.repositories.OperatorRepository;

@Service
public class OperatorsService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private OperatorRepository repo;
	
	public void getAll() {
		repo.findAll();
	}
	
	public void addOperator(Operator operator) {
		operator.setPassword(bCryptPasswordEncoder.encode(operator.getPassword()));
		repo.save(operator);
	}
	
	public Operator getOperatorByEmail(String email) {
		return repo.findByEmail(email);
	}

	public void deleteOperator(Operator operator) {
		repo.delete(operator);
	}

	public void deleteAll() {
		repo.deleteAll();
	}
	
	
}
