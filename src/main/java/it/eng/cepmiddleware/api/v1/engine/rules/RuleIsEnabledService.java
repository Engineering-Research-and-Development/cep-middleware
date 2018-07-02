package it.eng.cepmiddleware.api.v1.engine.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.Service;
import it.eng.cepmiddleware.engine.CEPEngineFactory;
import it.eng.cepmiddleware.rule.RuleCRUDService;

@org.springframework.stereotype.Service
public class RuleIsEnabledService implements Service {

	@Autowired GetRuleService getRuleService;
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
			return ruleIsEnabled(engineId, ruleId);
		}
		return paramError;
	}

	private ResponseEntity<?> ruleIsEnabled(String engineId, String ruleId) {
		ResponseEntity<?> getResponse =  getRuleService.execute(engineId, ruleId);
		ResponseEntity<?> ruleResponse = engineFactory.getCEPEngine(engineId).getRule(ruleId);
		if (getResponse.getStatusCode().is2xxSuccessful()) {
			if (ruleResponse.getStatusCode().is2xxSuccessful()) {
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			}
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

}
