package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.repositories.OperatorRepository;

@Service
public class OperatorService {

	@Autowired
	private OperatorRepository repo;
	
	public void getAll() {
		repo.findAll();

	}
}
