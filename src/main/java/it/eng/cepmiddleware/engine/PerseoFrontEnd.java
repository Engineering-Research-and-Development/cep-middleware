package it.eng.cepmiddleware.engine;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import it.eng.cepmiddleware.Converter;
import it.eng.cepmiddleware.rule.Rule;

public class PerseoFrontEnd implements CEPEngine {
	
	private String hostUrl;
	private String name;

	public PerseoFrontEnd(String name, String hostUrl) {
		this.hostUrl = hostUrl;
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	public String getHostUrl() {
		return hostUrl;
	}

	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}

	public ResponseEntity<?> createRule(Rule rule) {
		try {
			HttpResponse<Object> response = Unirest.post(hostUrl + "/rules")
			  .header("accept", "application/json")
			  .header("content-type", "application/json")
			  .body(rule)
			  .asObject(Object.class);
			return new ResponseEntity<Object>(
				response.getBody(),
				HttpStatus.resolve(response.getStatus())
			);
		} catch (UnirestException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_GATEWAY);
		}
	}
	
	public ResponseEntity<?> getRule(String ruleId) {
		try {
			HttpResponse<Object> response = Unirest.get(hostUrl + "/rules/" + ruleId)
			  .header("accept", "application/json")
			  .header("content-type", "application/json")
			  .asObject(Object.class);
			return new ResponseEntity<Object>(
				response.getBody(),
				HttpStatus.resolve(response.getStatus())
			);
		} catch (UnirestException e) {
			return new ResponseEntity<Void>(HttpStatus.BAD_GATEWAY);
		}
	}
	
	public ResponseEntity<?> getRules() {
		try {
			HttpResponse<Object> response = Unirest.get(hostUrl + "/rules")
			  .header("accept", "application/json")
			  .header("content-type", "application/json")
			  .asObject(Object.class);
			return new ResponseEntity<Object>(
				response.getBody(),
				HttpStatus.resolve(response.getStatus())
			);
		} catch (UnirestException e) {
			return new ResponseEntity<Void>(HttpStatus.BAD_GATEWAY);
		}
	}

	@Override
	public Converter<? extends Rule, Map<String, Object>> getConverter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> deleteRule(String ruleId) {
		// TODO Auto-generated method stub
		return null;
	}

}
