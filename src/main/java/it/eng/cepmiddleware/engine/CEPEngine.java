package it.eng.cepmiddleware.engine;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface CEPEngine {

	public ResponseEntity<?> createRule(Map<String, Object> rule);
	public ResponseEntity<?> getRule(String ruleId);
	public ResponseEntity<?> getRules();
	public ResponseEntity<?> updateRule(String ruleId, Map<String, Object> ruleMap);
	public ResponseEntity<?> deleteRule(String ruleId);
	public ResponseEntity<?> deleteAllRules();
	public ResponseEntity<?> getSupportedEventTypes();

}
