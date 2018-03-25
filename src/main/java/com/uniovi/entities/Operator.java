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
	private boolean isAdmin;
	private int numNotifications;

	/**
	 * Default constructor for the operator class
	 */
	public Operator() {
		super();
	}

	public Operator(String email, String operatorname, boolean isAdmin) {
		this();
		this.email = email;
		this.operatorname = operatorname;
		this.isAdmin = isAdmin;
	}

	public Operator(String email, String operatorname, String password, boolean isAdmin) {
		this(email, operatorname, isAdmin);
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
	public Operator(ObjectId id, String email, String operatorname, boolean isAdmin) {
		this(email, operatorname, isAdmin);
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
		return "Operator [email=" + email + ", operatorname=" + operatorname + ", isAdmin=" + isAdmin + "]";
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

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
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

}
