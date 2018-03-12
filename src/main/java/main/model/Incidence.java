package main.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import main.model.location.LatLng;

public class Incidence {

	String username;
	String incidenceName;
	String description;
	LatLng location;
	List<String> labels; // LABELS FOR THE INCIDENCE
	HashMap<String, String> fields; // PROPERTY VALUE
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
			LatLng location, List<String> labels,
			HashMap<String, String> fields, Status status, Date expiration) {
		this.username = username;
		this.incidenceName = incidenceName;
		this.description = description;
		this.location = location;
		this.labels = labels;
		this.fields = fields;
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
			LatLng location, List<String> labels) {
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

	public LatLng getLocation() {
		return location;
	}

	public void setLocation(LatLng location) {
		this.location = location;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public HashMap<String, String> getFields() {
		return fields;
	}

	public void setCampos(HashMap<String, String> fields) {
		this.fields = fields;
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