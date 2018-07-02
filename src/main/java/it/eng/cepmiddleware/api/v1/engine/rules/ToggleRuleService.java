package it.eng.cepmiddleware.api.v1.engine.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.Service;
import it.eng.cepmiddleware.engine.CEPEngineFactory;
import it.eng.cepmiddleware.rule.RuleCRUDService;

@org.springframework.stereotype.Service
public class ToggleRuleService implements Service {
	
	@Autowired RuleIsEnabledService ruleIsEnabledService;
	@Autowired CEPEngineFactory engineFactory;
	
	ResponseEntity<String> paramError = new ResponseEntity<String>(
		"Correct parameters not provided",
		HttpStatus.BAD_REQUEST
	);

	@Override
	public ResponseEntity<?> execute(Object... parameters) {
		if (
			parameters[0] instanceof String &&
			parameters[1] instanceof String &&
			parameters[2] instanceof Boolean
		) {
			String engineId = (String) parameters[0];
			String ruleId = (String) parameters[1];
			Boolean enableSwitch = (Boolean) parameters[2];
			return toggleRule(engineId, ruleId, enableSwitch);
		}
		return paramError;
	}

	private ResponseEntity<?> toggleRule(String engineId, String ruleId, Boolean enableSwitch) {
		ResponseEntity<?> ruleEnabledResponse = ruleIsEnabledService.execute(engineId, ruleId);
		if (ruleEnabledResponse.getStatusCode().is2xxSuccessful()) {
			if (enableSwitch) {
				try {
					engineFactory.getCEPEngine(engineId).getMiddlewareCRUD().read(ruleId).<ResponseEntity<?>>map(rule -> {
						return engineFactory.getCEPEngine(engineId).createRule(rule.getClass().cast(rule));
					}).orElse(new ResponseEntity<>("Couldn't switch the rule on :'(", HttpStatus.BAD_REQUEST));
				} catch (Exception e) {
					return new ResponseEntity<>("Couldn't switch the rule on :'(", HttpStatus.BAD_REQUEST);
				}
			} else {
				return engineFactory.getCEPEngine(engineId).deleteRule(ruleId).getStatusCode().is2xxSuccessful()?
						new ResponseEntity<>("Rule disabled", HttpStatus.OK):
						new ResponseEntity<>("Disable failed", HttpStatus.BAD_REQUEST);
			}
		}
		return ruleEnabledResponse;
	}

}
