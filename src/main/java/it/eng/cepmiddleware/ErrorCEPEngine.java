package it.eng.cepmiddleware;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorCEPEngine implements CEPEngine {
	
	private String errorMessage = "This is not the CEP Engine you are looking for...";
	
	private ResponseEntity<String> errorResponse = new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	
	public String getErrorMessage() {
		return this.errorMessage;
	}

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

	@Override
	public void accept(CEPEngineVisitor visitor) {
		visitor.visit(this);
	}

}
