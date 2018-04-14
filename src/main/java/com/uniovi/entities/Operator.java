package com.uniovi.entities;

import java.util.HashSet;
import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.uniovi.util.Checker;

@Document(collection="operators")
public class Operator {

	@Id
	private ObjectId id;
	
	private String email;
	private String password;
	private String operatorname;
	private String role;
	private int numNotifications;
	private Set<String> sectionsAllowed;
	
	/**
	 * Default constructor for the operator class
	 */
	public Operator() {
		super();
		this.sectionsAllowed = new HashSet<>();
		sectionsAllowed.add("maps");
		sectionsAllowed.add("charts");
		sectionsAllowed.add("incidents");
	}

	public Operator(String email, String operatorname, String role) {
		this();
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
	
	public Set<String> getSectionsAllowed() {
		return sectionsAllowed;
	}

	public void setSectionsAllowed(Set<String> sectionsAllowed) {
		this.sectionsAllowed = sectionsAllowed;
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

	private void addToSectionsAllowed(String section){
		Checker.isEmpty(section);
		Checker.isNull(section);
		this.sectionsAllowed.add(section);
	}
}
