package com.uniovi.entities.location;

import javax.persistence.Embeddable;

import com.uniovi.util.Checker;

/**
 * 
 * @author Sergio Faya Fernández
 *
 */
@Embeddable
public class LatLng {

	public double latitude;
	public double longitude;
	
	public LatLng() {}
	
	public LatLng(double latitude, double longitude) {
		Checker.isNull(latitude);
		Checker.isNull(longitude);
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	@Override
	public String toString() {
		return "Location{Latitude='"+latitude+"',"+
				"Longitude='"+longitude+"'}";
	}
}
