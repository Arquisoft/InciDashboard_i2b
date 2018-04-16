package com.uniovi.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="operators")
public class Operator {

	@Id
	private ObjectId id;
	private String email;
	private String password;
	private String operatorname;
	private String role;
	private int numNotifications;
	private boolean mapAccess;
	private boolean chartAccess;
	private boolean incidentModify;
	private boolean admin;
	

	/**
	 * Default constructor for the operator class
	 */
	public Operator() {
		super();
	}

	public Operator(String email, String operatorname, String role) {
		this();
		this.mapAccess =  true;
		this.chartAccess =  true;
		this.incidentModify =  true;
		this.email = email;
		this.operatorname = operatorname;
		this.role = role;
	}

	public Operator(String email, String operatorname, String password, String role) {
		this(email, operatorname, role);
		this.password = password;
	}

	/**
	 * Constructor with all the parameters for the operator class
	 * 
	 * @param id
	 * @param email
	 * @param operatorname
	 * @param isAdmin
	 */
	public Operator(ObjectId id, String email, String operatorname, String role) {
		this(email, operatorname, role);
		this.id = id;

	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operator other = (Operator) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Operator [id=" + id + ", email=" + email + ", password=" + password + ", operatorname=" + operatorname
				+ ", role=" + role + ", numNotifications=" + numNotifications + "]";
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOperatorname() {
		return operatorname;
	}

	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
	
	public int getNumNotifications() {
		return this.numNotifications;
	}
	
	public void setNumNotifications(int numNotifications) {
		this.numNotifications = numNotifications;
	}

	public boolean getIsAdmin() {
		return role.equals("ROLE_ADMIN");
	}

	public void setIsAdmin(boolean b) {
		if(b) {
			this.role = "ROLE_ADMIN";
		}else {
			this.role = "ROLE_OPERATOR";
		}
		
	}

	public boolean isMapAccess() {
		return mapAccess;
	}

	public void setMapAccess(boolean mapAccess) {
		this.mapAccess = mapAccess;
	}

	public boolean isChartAccess() {
		return chartAccess;
	}

	public void setChartAccess(boolean chartAccess) {
		this.chartAccess = chartAccess;
	}


	public boolean isIncidentModify() {
		return incidentModify;
	}

	public void setIncidentModify(boolean incidentModify) {
		this.incidentModify = incidentModify;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	
}
