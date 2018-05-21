package it.eng.cepmiddleware;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorCEPEngine implements CEPEngine {
	
	private ResponseEntity<String> errorResponse = new ResponseEntity<String>(
		"This is an ErrorEngine",
		HttpStatus.NOT_FOUND
	);

	@Override
	public ResponseEntity<?> createRule(CEPRule rule) {
		return errorResponse;
	}

	@Override
	public ResponseEntity<?> getRule(String ruleId) {
		return errorResponse;
	}

	@Override
	public ResponseEntity<?> getRules() {
		return errorResponse;
	}

}
