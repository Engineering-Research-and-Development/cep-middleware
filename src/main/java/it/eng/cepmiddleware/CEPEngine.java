package it.eng.cepmiddleware;

import org.springframework.http.ResponseEntity;

public interface CEPEngine {
	
	public ResponseEntity<?> createRule(CEPRule rule);
	public ResponseEntity<?> getRule(String ruleId);
	public ResponseEntity<?> getRules();
	
}
