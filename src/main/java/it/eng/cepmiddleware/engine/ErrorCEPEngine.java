package it.eng.cepmiddleware.engine;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorCEPEngine implements CEPEngine {

	private String errorMessage = "The engine you chose does not exist or was not correctly configured";

	private ResponseEntity<String> errorResponse = new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);

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

}
