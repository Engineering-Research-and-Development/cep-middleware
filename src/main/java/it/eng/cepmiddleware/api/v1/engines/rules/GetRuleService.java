package it.eng.cepmiddleware.api.v1.engines.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.Service;
import it.eng.cepmiddleware.engine.CEPEngineProvider;

@org.springframework.stereotype.Service
public class GetRuleService implements Service {
	
	@Autowired CEPEngineProvider engineProvider;
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
		return engineProvider.getCEPEngine(engineId).getRule(ruleId);
	}

}
