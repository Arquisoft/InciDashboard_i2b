package com.uniovi.entities.location;

import org.springframework.data.mongodb.core.mapping.Field;

import com.uniovi.util.Checker;

/**
 * 
 * @author Sergio Faya Fernández
 *
 */
public class LatLng {

	@Field("lat")
	public double latitude;

	@Field("lon")
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
