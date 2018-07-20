package it.eng.cepmiddleware.engine.perseo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import it.eng.cepmiddleware.rule.PerseoRule;

public class PerseoWebService {
	private String hostUrl;

	public PerseoWebService(String hostUrl) {
		this.hostUrl = hostUrl;
	}

	public ResponseEntity<?> createRule(PerseoRule rule) {
		PerseoRuleAdapter adaptedRule = new PerseoRuleAdapter(rule);
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
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> getRule(String ruleName) {
		try {
			HttpResponse<PerseoResponse> response = Unirest.get(hostUrl + "/rules/" + ruleName)
				.header("accept", "application/json")
				.header("content-type", "application/json")
				.asObject(PerseoResponse.class)
			;
			return new ResponseEntity<PerseoRule>(
				new NativeToMiddlewarePerseoRule(response.getBody().getData()),
				HttpStatus.resolve(response.getStatus())
			);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> deleteRule(String ruleName) {
		try {
			HttpResponse<Object> response = Unirest.delete(hostUrl + "/rules/" + ruleName)
			  .header("accept", "application/json")
			  .header("content-type", "application/json")
			  .asObject(Object.class);
			return new ResponseEntity<Void>(
				HttpStatus.resolve(response.getStatus())
			);
		} catch (UnirestException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> updateRule(PerseoRule rule) {
		String ruleName = rule.getName();
		Object mistery = this.getRule(ruleName).getBody();
		if (mistery instanceof PerseoRule) {
			PerseoRule oldRule = (PerseoRule)mistery;
			if (deleteRule(ruleName).getStatusCode().is2xxSuccessful()) {
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
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public PerseoRuleValidity checkRuleValidity(PerseoRule rule) {
		String ruleName = rule.getName();
		ResponseEntity ruleCreation = this.createRule(rule);
		if (ruleCreation.getStatusCode().is2xxSuccessful()) {
			this.deleteRule(rule.getName());
			rule.setName(ruleName);
			return PerseoRuleValidity.valid();
		}
		return new PerseoRuleValidity(false, ruleCreation.getBody().toString());
	}

}
