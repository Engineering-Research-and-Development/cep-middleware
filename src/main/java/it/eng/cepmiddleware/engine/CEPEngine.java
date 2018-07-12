package it.eng.cepmiddleware.engine;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.CRUDService;
import it.eng.cepmiddleware.Converter;
import it.eng.cepmiddleware.rule.Rule;

public interface CEPEngine {

	public ResponseEntity<?> createRule(Map<String, Object> rule);
	public ResponseEntity<?> getRule(String ruleId);
	public ResponseEntity<?> getRules();
	public ResponseEntity<?> updateRule(String ruleId, Map<String, Object> ruleMap);
	public ResponseEntity<?> deleteRule(String ruleId);

}
