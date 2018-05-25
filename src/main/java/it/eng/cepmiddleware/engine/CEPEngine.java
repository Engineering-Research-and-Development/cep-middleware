package it.eng.cepmiddleware.engine;

import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.rule.Rule;

public interface CEPEngine {
	
	public ResponseEntity<?> createRule(Rule rule);
	public ResponseEntity<?> getRule(String ruleId);
	public ResponseEntity<?> getRules();
	public void accept(CEPEngineVisitor visitor);
	
}
