package it.eng.cepmiddleware;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.ObjectMapper;

public class UnirestObjectMapper implements ObjectMapper {
    private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
    = new com.fasterxml.jackson.databind.ObjectMapper();

	public <T> T readValue(String value, Class<T> valueType) {
		try {
			return jacksonObjectMapper.readValue(value, valueType);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String writeValue(Object value) {
		try {
			return jacksonObjectMapper.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
