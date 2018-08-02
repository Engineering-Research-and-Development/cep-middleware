package it.eng.cepmiddleware;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectToMapConverter {

	private static ObjectMapper oMapper = new ObjectMapper();

	public static Map<String, Object> convert(Object source) {
		return oMapper.convertValue(source, Map.class);
	}

}
