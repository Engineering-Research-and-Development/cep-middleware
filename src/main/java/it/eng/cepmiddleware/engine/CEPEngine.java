package it.eng.cepmiddleware.engine;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.Converter;
import it.eng.cepmiddleware.rule.Rule;

public interface CEPEngine {

	public String getName();
	public ResponseEntity<?> createRule(Rule rule);
	public ResponseEntity<?> getRule(String ruleId);
	public ResponseEntity<?> getRules();
	public ResponseEntity<?> updateRule(Rule rule);
	public ResponseEntity<?> deleteRule(String ruleId);
	public ResponseEntity<?> postEvent(Object event);
	public Converter<? extends Rule, Map<String, Object>> getRuleConverter();

}
