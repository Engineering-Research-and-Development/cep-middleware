package it.eng.cepmiddleware.api.v1.engine.rules;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.Service;
import it.eng.cepmiddleware.engine.CEPEngineFactory;
import it.eng.cepmiddleware.rule.Rule;
import it.eng.cepmiddleware.rule.RuleRepository;

@org.springframework.stereotype.Service
public class GetRuleService implements Service {
	
	@Autowired RuleRepository repository;
	@Autowired CEPEngineFactory engineFactory;
	ResponseEntity<String> paramError = new ResponseEntity<String>(
		"Correct parameters not provided",
		HttpStatus.BAD_REQUEST
	);

	@Override
	public ResponseEntity<?> execute(Object... parameters) {
		if (parameters[0] instanceof String && parameters[1] instanceof String) {
			String engineId = (String) parameters[0];
			String ruleId = (String) parameters[1];
			return getRuleService(engineId, ruleId);
		}
		return paramError;
	}

	private ResponseEntity<?> getRuleService(String engineId, String ruleId) {
		Optional<Rule> maybeRule = Optional.ofNullable(repository.getRuleById(ruleId));
		return maybeRule.<ResponseEntity<?>>map( (rule) -> {
			return new ResponseEntity<Rule>(rule, HttpStatus.OK);
		}).orElse(new ResponseEntity<String>("Rule doesnt exist", HttpStatus.NOT_FOUND));
	}

}
