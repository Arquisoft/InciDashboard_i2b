package com.uniovi.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.uniovi.entities.Incident;

/**
 * Serializes an Incident object to a JSON string.
 */
public class IncidentSerializer extends JsonSerializer<Incident> {

	@Override
	public void serialize(Incident incident, JsonGenerator generator,
			SerializerProvider provider) throws IOException {
		
		generator.writeStartObject();
		
		// agent info
		generator.writeObjectFieldStart("agent");
		generator.writeStringField("username", incident.getAgent().getUsername());
		generator.writeStringField("password", incident.getAgent().getPassword());
		generator.writeStringField("kind", incident.getAgent().getKind());
		generator.writeEndObject();
		
		generator.writeStringField("inciName", incident.getInciName());
		
		// location
		generator.writeObjectFieldStart("location");
		generator.writeNumberField("lat", incident.getLocation().latitude);
		generator.writeNumberField("lon", incident.getLocation().longitude);
		generator.writeEndObject();
		
		// tags
		generator.writeArrayFieldStart("tags");
		for (String tag : incident.getTags()) {
			generator.writeString(tag);
		}
		generator.writeEndArray();
		
		// more info
		generator.writeArrayFieldStart("moreInfo");
		for (String info : incident.getMoreInfo()) {
			generator.writeString(info);
		}
		generator.writeEndArray();
		
		// properties
		generator.writeObjectFieldStart("properties");
		for (String key : incident.getProperties().keySet()) {
			generator.writeObjectField(key, incident.getProperties().get(key));
		}
		generator.writeEndObject();
		
		generator.writeEndObject();
	}

}
