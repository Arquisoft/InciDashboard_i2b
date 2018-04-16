package com.uniovi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Incident;
import com.uniovi.entities.Operator;
import com.uniovi.repositories.OperatorRepository;

@Service
public class OperatorsService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private OperatorRepository repo;
	
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

	/**
	 * Increases the notification count of the operator
	 * which the current incident belongs to.
	 * @param incident
	 */
	public void increaseNotificationCount(Incident incident) {
		String opEmail = incident.getOperator();
		if (opEmail == null) {
			return;
		}
		
		Operator operator = repo.findByEmail(opEmail);
		if (operator != null) {
			operator.setNumNotifications(operator.getNumNotifications() + 1);
			repo.save(operator);
		}
	}
	
	public void resetNotificationCount(Operator operator) {
		operator.setNumNotifications(0);
		repo.save(operator);
	}

	public boolean checkPassword(Operator operator) {
		return repo.isInDb(operator.getEmail(), operator.getPassword()) != null ;
	}

	public List<Operator> getAllOperatorsBut(String name) {
		List<Operator> ops = repo.findAll();
		ops.removeIf(op -> op.getEmail().equals(name));
		return ops;
	}

	public void changePermissions(String id) {
		String[] idsplitted = id.split("-");
		String option = idsplitted[0];
		String operatorId = idsplitted[1];
		Operator operator = getOperatorByEmail(operatorId);
		switch (option) {
		case "map":
			operator.setMapAccess(!operator.isMapAccess());
			repo.save(operator);
			break;
		case "chart":
			operator.setChartAccess(!operator.isChartAccess());
			repo.save(operator);
			break;
		case "incidents":
			operator.setIncidentModify(!operator.isIncidentModify());
			repo.save(operator);
			break;
		case "admin":
			operator.setAdmin(!operator.isAdmin());
			repo.save(operator);
			break;
		default:
			break;
		}		
	}
	
	
}
