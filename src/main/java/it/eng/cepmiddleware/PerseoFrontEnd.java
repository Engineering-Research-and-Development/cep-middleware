package it.eng.cepmiddleware;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class PerseoFrontEnd implements CEPEngine {
	
	private String hostUrl;

	public PerseoFrontEnd(String hostUrl) {
		this.hostUrl = hostUrl;
	}
	
	public String getHostUrl() {
		return hostUrl;
	}

	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}

	public ResponseEntity<?> createRule(CEPRule rule) {
		try {
			HttpResponse<Object> response = Unirest.post(hostUrl + "/rules")
			  .header("accept", "application/json")
			  .header("content-type", "application/json")
			  .body(new PerseoFERule(rule))
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
	public void accept(CEPEngineVisitor visitor) {
		visitor.visit(this);
	}
}
