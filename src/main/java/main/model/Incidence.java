package main.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Incidence {

	String username;
	String incidenceName;
	String description;
	String location;
	List<String> labels; // LABELS FOR THE INCIDENCE
	HashMap<String, String> campos; // PROPERTY VALUE
	Status status;
	Date expiration; // PARA LOS SENSORES

	/**
	 * Constructor
	 *
	 * @param username
	 * @param incidenceName
	 * @param description
	 * @param location
	 * @param labels
	 * @param campos
	 * @param status
	 * @param expiration
	 */
	public Incidence(String username, String incidenceName, String description,
			String location, List<String> labels,
			HashMap<String, String> campos, Status status, Date expiration) {
		this.username = username;
		this.incidenceName = incidenceName;
		this.description = description;
		this.location = location;
		this.labels = labels;
		this.campos = campos;
		this.status = status;
		this.expiration = expiration;
	}

	/**
	 * Constructor that has the must-have parameters
	 *
	 * @param username
	 * @param incidenceName
	 * @param description
	 * @param location
	 * @param labels
	 */
	public Incidence(String username, String incidenceName, String description,
			String location, List<String> labels) {
		this.username = username;
		this.incidenceName = incidenceName;
		this.description = description;
		this.location = location;
		this.labels = labels;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIncidenceName() {
		return incidenceName;
	}

	public void setIncidenceName(String incidenceName) {
		this.incidenceName = incidenceName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public HashMap<String, String> getCampos() {
		return campos;
	}

	public void setCampos(HashMap<String, String> campos) {
		this.campos = campos;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	/**
	 * toString method
	 * 
	 * Prints username, incidence name, location and status
	 */
	@Override
	public String toString() {
		return "Incidence [username=" + username + ", incidenceName="
				+ incidenceName + ", location=" + location + ", status="
				+ status + "]";
	}
	
	
}