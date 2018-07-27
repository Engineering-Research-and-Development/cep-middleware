package it.eng.cepmiddleware.engine;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.responses.PlainResponseBody;

public class ErrorCEPEngine implements CEPEngine {

	private String errorMessage = "Engine doesn't exist";

	private ResponseEntity errorResponse =  new ResponseEntity<>(
		new PlainResponseBody(errorMessage),
		HttpStatus.NOT_FOUND
	);

	public ErrorCEPEngine() {}
	public ErrorCEPEngine(String name, String hostUrl) {}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	@Override
	public ResponseEntity<?> getRule(String ruleId) {
		return errorResponse;
	}

	@Override
	public ResponseEntity<?> getRules() {
		return errorResponse;
	}

	@Override
	public ResponseEntity<?> createRule(Map<String, Object> rule) {
		return errorResponse;
	}

	@Override
	public ResponseEntity<?> updateRule(String ruleId, Map<String, Object> ruleMap) {
		return errorResponse;
	}

	@Override
	public ResponseEntity<?> deleteRule(String ruleId) {
		return errorResponse;
	}

	@Override
	public ResponseEntity<?> deleteAllRules() {
		return errorResponse;
	}

	@Override
	public ResponseEntity<?> getSupportedEventTypes() {
		return errorResponse;
	}

	@Override
	public void deactivateAllRules() {}

}
