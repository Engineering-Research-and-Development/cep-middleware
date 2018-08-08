package it.eng.cepmiddleware.api.v1.exposed_engine.rules;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.Service;
import it.eng.cepmiddleware.engine.EngineExposer;

@org.springframework.stereotype.Service
public class UpdateExposedEngineRuleService implements Service {

	@Autowired private EngineExposer engineExposer;

	@Override
	public ResponseEntity<?> execute(Object... parameters) {
		if (
			parameters[0] instanceof String &&
			parameters[1] instanceof Map
		) {
			String ruleId = (String) parameters[0];
			Map<String, Object> ruleMap = (Map<String, Object>) parameters[1];
			return updateRule(ruleId, ruleMap);
		}
		return ResponseEntity.badRequest().build();
	}

	private ResponseEntity<?> updateRule(String ruleId, Map<String, Object> ruleMap) {
		return engineExposer.getExposedEngine().updateRule(ruleId, ruleMap);
	}

}
