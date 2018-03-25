package com.uniovi.util;

import java.util.Map;

public class IncidentPropertiesConverter {

	private JSONHelper jsonHelper = new JSONHelper();
	
	public String convertToDatabaseColumn(Map<String, Object> properties) {
		return jsonHelper.mapToJson(properties);
	}

	public Map<String, Object> convertToEntityAttribute(String json) {
		return jsonHelper.jsonToMap(json);
	}
	
}
