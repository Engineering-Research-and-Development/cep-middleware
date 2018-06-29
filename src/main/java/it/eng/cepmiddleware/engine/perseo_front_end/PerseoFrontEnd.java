package it.eng.cepmiddleware.engine.perseo_front_end;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import it.eng.cepmiddleware.Converter;
import it.eng.cepmiddleware.engine.CEPEngine;
import it.eng.cepmiddleware.rule.PerseoFERule;
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
		PerseoFERuleAdapter adaptedRule = new PerseoFERuleAdapter(rule);
		try {
			HttpResponse<Object> response = Unirest.post(hostUrl + "/rules")
			  .header("accept", "application/json")
			  .header("content-type", "application/json")
			  .body(adaptedRule)
			  .asObject(Object.class);
			return new ResponseEntity<Object>(
				response.getBody(),
				HttpStatus.resolve(response.getStatus())
			);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_GATEWAY);
		}
	}
	
	public ResponseEntity<?> getRule(String ruleId) {
		try {
			HttpResponse<PerseoFEResponse> response = Unirest.get(hostUrl + "/rules/" + ruleId)
			  .header("accept", "application/json")
			  .header("content-type", "application/json")
			  .asObject(PerseoFEResponse.class);
			return new ResponseEntity<PerseoFERule>(
				new NativeToUniformPerseoFrontEndRule(response.getBody().getData()),
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
	public ResponseEntity<?> deleteRule(String ruleId) {
		try {
			HttpResponse<Object> response = Unirest.delete(hostUrl + "/rules/" + ruleId)
			  .header("accept", "application/json")
			  .header("content-type", "application/json")
			  .asObject(Object.class);
			return new ResponseEntity<Void>(
				HttpStatus.resolve(response.getStatus())
			);
		} catch (UnirestException e) {
			return new ResponseEntity<Void>(HttpStatus.BAD_GATEWAY);
		}
	}

	@Override
	public ResponseEntity<?> updateRule(Rule rule) {
		String ruleId = rule.getRuleId();
		Object mistery = getRule(ruleId).getBody();
		if (mistery instanceof PerseoFERule) {
			PerseoFERule oldRule = (PerseoFERule)mistery;
			if (deleteRule(ruleId).getStatusCode().is2xxSuccessful()) {
				if (createRule(rule).getStatusCode().is2xxSuccessful()) {
					return ResponseEntity.ok().build();
				} else {
					createRule(oldRule);
					return new ResponseEntity<Void>(HttpStatus.METHOD_NOT_ALLOWED);
				}
			}
			
		}
		return ResponseEntity.badRequest().build();
	}

	@Override
	public ResponseEntity<?> postEvent(Object event) {
		try {
			HttpResponse response = Unirest.post(hostUrl + "/notices")
				.header("content-type", "application/json")
				.body(event)
				.asObject(Object.class);
			return new ResponseEntity<>(
				response.getBody(),
				HttpStatus.valueOf(response.getStatus())
			);
		} catch (UnirestException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@Override
	public Converter<? extends Rule, Map<String, Object>> getRuleConverter() {
		return new PerseoFERuleConverter(this.getName());
	}

}
