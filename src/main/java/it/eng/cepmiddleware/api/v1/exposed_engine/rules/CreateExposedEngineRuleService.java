package it.eng.cepmiddleware.api.v1.exposed_engine.rules;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.Service;
import it.eng.cepmiddleware.engine.EngineExposer;

@org.springframework.stereotype.Service
public class CreateExposedEngineRuleService implements Service {

	@Autowired private EngineExposer engineExposer;

	@Override
	public ResponseEntity<?> execute(Object... parameters) {
		if (parameters[0] instanceof Map) {
			Map<String, Object> ruleMap = (Map<String, Object>) parameters[0];
			return createRule(ruleMap);
		}
		return ResponseEntity.badRequest().build();
	}

	private ResponseEntity<?> createRule(Map<String, Object> ruleMap) {
		return engineExposer.getExposedEngine().createRule(ruleMap);
	}

}
