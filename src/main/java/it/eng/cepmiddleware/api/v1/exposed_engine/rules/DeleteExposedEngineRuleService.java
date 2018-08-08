package it.eng.cepmiddleware.api.v1.exposed_engine.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.Service;
import it.eng.cepmiddleware.engine.EngineExposer;

@org.springframework.stereotype.Service
public class DeleteExposedEngineRuleService implements Service {

	@Autowired private EngineExposer engineExposer;

	ResponseEntity<String> paramError = new ResponseEntity<String>(
		"Correct parameters not provided",
		HttpStatus.BAD_REQUEST
	);

	@Override
	public ResponseEntity<?> execute(Object... parameters) {
		if (parameters[0] instanceof String) {
			String ruleId = (String) parameters[0];
			return deleteRuleService(ruleId);
		}
		return paramError;
	}

	private ResponseEntity<?> deleteRuleService(String ruleId) {
		return engineExposer.getExposedEngine().deleteRule(ruleId);
	}

}
