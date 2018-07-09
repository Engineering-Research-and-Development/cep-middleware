package it.eng.cepmiddleware.engine;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.CRUDService;
import it.eng.cepmiddleware.Converter;
import it.eng.cepmiddleware.rule.Rule;

public class ErrorCEPEngine implements CEPEngine {

	private String errorMessage = "This is not the CEP Engine you are looking for...";

	private ResponseEntity<String> errorResponse = new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);

	public ErrorCEPEngine() {}
	public ErrorCEPEngine(String name, String hostUrl) {}

	@Override
	public String getName() {
		return "error";
	}

	@Override
	public String getURL() {
		return "invalid";
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	@Override
	public ResponseEntity<?> createRule(Rule rule) {
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
	public ResponseEntity<?> deleteRule(String ruleId) {
		return errorResponse;
	}

	@Override
	public ResponseEntity<?> updateRule(Rule rule) {
		return errorResponse;
	}

	@Override
	public ResponseEntity<?> postEvent(Object event) {
		return errorResponse;
	}

	@Override
	public Converter<? extends Rule, Map<String, Object>> getRuleConverter() {
		return null;
	}
	@Override
	public CRUDService getMiddlewareCRUD() throws Exception {
		throw new Exception(errorMessage);
	}

}
